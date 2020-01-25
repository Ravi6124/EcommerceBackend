package com.example.cartAndOrder.services;


import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.entity.Order;
import com.example.cartAndOrder.exchanges.cartExchanges.CartProduct;
import com.example.cartAndOrder.exchanges.cartExchanges.KafkaMailObject;
import com.example.cartAndOrder.exchanges.orderExchanges.CheckOutResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.FindOrdersByMidResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.GetOrdersByUserIdResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges.CheckAndUpdateRequest;
import com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges.CheckStockResponse;
import com.example.cartAndOrder.feignClient.MerchantClient;
import com.example.cartAndOrder.repository.CartRepository;
import com.example.cartAndOrder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableAsync
public class OrderServicesImpl implements OrderServices {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    KafkaTemplate<String,KafkaMailObject> kafkaTemplate;

    @Autowired
    MerchantClient merchantClient;

    @Autowired
    CartRepository cartRepository;
    @Override
    public CheckOutResponse checkOut(Order order,Cart cart) {

        List<CartProduct> cartProducts = order.getItems();

        CheckOutResponse response = new CheckOutResponse();


//        //TODO: Check and Update the Merchant stock of product if stock not available then return

        //checking stock for each and every product

        CheckAndUpdateRequest checkAndUpdateRequest= new CheckAndUpdateRequest();
        checkAndUpdateRequest.setCartProducts(cartProducts);

        CheckStockResponse checkStockResponse = merchantClient.checkProductStock(checkAndUpdateRequest);

        if(!checkStockResponse.isStatus()){
            response.setStatus(checkStockResponse.isStatus());
            response.setUnavailableStock(checkStockResponse.getList());

            return response;
        }

        //updating everyStock


        merchantClient.updateStock(checkAndUpdateRequest);



        //Adding to the orderRepository
        Order order1 = orderRepository.save(order);
        response.setOrderId(order1.getOrderId());
        response.setStatus(true);
//
//        clearing the cart
//        Optional<Cart> cart1 = cartRepository.findById(order1.getUserId());
//        if(cart1.isPresent()){
//            cartRepository.delete(cart1.get());
//        }
//
        cartRepository.delete(cart);

//




        //TODO: Send Email
       String message = "<h1>Your order details are </h1> <br> \n" + order.toString();
        try{
            mail("antassinha@gmail.com",message);
        }catch (Exception e){
            e.printStackTrace();
        }


        System.out.println("OrderPlaced");



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
    @Async
    public void mail(String email,String message) throws MessagingException {

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
        //emailMessage= "MAil sent";
        messageBodyPart.setContent(message, "text/html");


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

//    public void send(String email,String message){
//        KafkaMailObject kafkaMailObject = new KafkaMailObject();
//        kafkaMailObject.setEmail(email);
//        kafkaMailObject.setMessage(message);
//
//        kafkaTemplate.send(email,kafkaMailObject);
//
//
//    }
//
//    @KafkaListener(topics = "email")
//    public void listen(KafkaMailObject kafkaMailObject){
//
//        try{
//            mail(kafkaMailObject.getEmail(),kafkaMailObject.getMessage());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}
