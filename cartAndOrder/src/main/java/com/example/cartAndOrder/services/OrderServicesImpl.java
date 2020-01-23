package com.example.cartAndOrder.services;


import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.entity.Order;
import com.example.cartAndOrder.exchanges.CartProduct;
import com.example.cartAndOrder.exchanges.orderExchanges.CheckOutResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.FindOrdersByMidResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.GetOrdersByUserIdResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.UnavailableStock;
import com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges.CheckAndUpdateRequest;
import com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges.CheckAndUpdateResponse;
import com.example.cartAndOrder.feignClient.CustomerClient;
import com.example.cartAndOrder.feignClient.MerchantClient;
import com.example.cartAndOrder.feignClient.ProductClient;
import com.example.cartAndOrder.repository.CartRepository;
import com.example.cartAndOrder.repository.OrderRepository;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServicesImpl implements OrderServices {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;
    @Override
    public CheckOutResponse checkOut(Order order) {

        List<CartProduct> cartProducts = order.getItems();
        Iterator<CartProduct> iterator1 = cartProducts.iterator();
        Iterator<CartProduct> iterator = cartProducts.iterator();
        CheckOutResponse response = new CheckOutResponse();


        //TODO: Check and Update the Merchant stock of product if stock not available then return
        MerchantClient merchantClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(MerchantClient.class, "172.16.20.255:8080/checkStockAndUpdate");

        //checking stock for each and every product
        int flag = 0;
        while (iterator.hasNext()){
            CartProduct cartProduct = iterator.next();
            CheckAndUpdateRequest checkAndUpdateRequest= new CheckAndUpdateRequest();

            //Setting the params for the request
            checkAndUpdateRequest.setMerchantId(cartProduct.getMerchantId());
            checkAndUpdateRequest.setProductId(cartProduct.getProductId());
            checkAndUpdateRequest.setQuantity(cartProduct.getQuantity());

            CheckAndUpdateResponse checkAndUpdateResponse = merchantClient.checkProductStockAndUpdate(checkAndUpdateRequest);

            //Adding to list of unavailable products if stock does not match
            if(!checkAndUpdateResponse.isStatus()){
                response.setStatus(false);
                response.getUnavailableStock().add(new UnavailableStock(cartProduct.getProductName(),cartProduct.getQuantity()));
                flag = 1;

            }
        }

        if(flag == 1){
            return  response;
        }


        //Adding to the orderRepository
        Order order1 = orderRepository.save(order);
        response.setOrderId(order1.getOrderId());
        response.setStatus(true);

        //clearing the cart
        Optional<Cart> cart = cartRepository.findById(order1.getUserId());
        if(cart.isPresent()){
            cartRepository.delete(cart.get());
        }

        // Updating the Product Stock
        ProductClient productClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ProductClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ProductClient.class, "http://172.16.20.119:8081/product/update");

        while (iterator1.hasNext()){
            CartProduct cartProduct = iterator1.next();

            productClient.updateStock(cartProduct.getProductId(),-cartProduct.getQuantity());
        }




        //TODO: Send Email
        CustomerClient customerClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(CustomerClient.class))
                .logLevel(Logger.Level.FULL)
                .target(CustomerClient.class, "http://172.16.20.119:8080/customer/email");

        String email = customerClient.getEmail(order.getUserId());
        try{
            mail(email);
        }catch (Exception e){
            e.printStackTrace();
        }



        return response ;


    }

    @Override
    public GetOrdersByUserIdResponse findOrdersByUserId(String userId) {

        List<Order> orders = orderRepository.findByUserId(userId);

        GetOrdersByUserIdResponse getOrdersByUserIdResponse = new GetOrdersByUserIdResponse();
        getOrdersByUserIdResponse.setOrders(orders);
        return getOrdersByUserIdResponse;
    }

    @Override
    public void mail(String email) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("leagueofavengersecom@gmail.com", "thzrnpopewvejula");
            }
        });

        //compose message

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(email, false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Your Order Summary :");
        msg.setContent("Your Order Summary :", "text/html");
        msg.setSentDate(new Date());
        MimeBodyPart messageBodyPart = new MimeBodyPart();

        String emailMessage;
        emailMessage= "MAil sent";
        messageBodyPart.setContent(emailMessage, "text/html");


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart);
        Transport.send(msg);

        System.out.println("Email sent");


    }

    @Override
    public List<FindOrdersByMidResponse> findOrdersByMid(String mid) {

        //finding a list of all the orders Placed
        //TODO: can add a filter based on the date till which the merchant wants to see the orders
        List<Order> orderList = orderRepository.findAll();

        //List of all the order responses that can be sent
        List<FindOrdersByMidResponse> response = new ArrayList<>();

        //traversing through the List of orders to see if ANY of the products match the given MID

        Iterator<Order> orderIterator = orderList.iterator();
        while(orderIterator.hasNext()){
            Order order = orderIterator.next();

            //checking if the any product of the orderList has the given MID
            List<CartProduct> cartProducts = order.getItems();
            //list of all the cartproducts that were from same MID
            List<CartProduct> list = cartProducts.stream().filter(cartProduct -> {return cartProduct.getMerchantId().equals(mid);}).collect(Collectors.toList());


            Iterator<CartProduct> cartProductIterator = list.iterator();

            while(cartProductIterator.hasNext()){
                CartProduct cartProduct = cartProductIterator.next();

                FindOrdersByMidResponse ordersByMidResponse =new FindOrdersByMidResponse();

                //setting response for the response list object
                ordersByMidResponse.setImageURL(cartProduct.getImageURL());
                ordersByMidResponse.setOrderDate(order.getDate());
                ordersByMidResponse.setPrice(cartProduct.getPrice());
                ordersByMidResponse.setCustomerId(order.getUserId());
                ordersByMidResponse.setQuantity(cartProduct.getQuantity());
                ordersByMidResponse.setProductName(cartProduct.getProductName());

                //adding object to response list
                response.add(ordersByMidResponse);

            }

        }


        return response;

    }
}
