package com.example.cartAndOrder.services;

import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.exchanges.cartExchanges.CartModifiedResponse;
import com.example.cartAndOrder.exchanges.cartExchanges.CartProduct;
import com.example.cartAndOrder.repository.CartRepository;
import com.example.cartAndOrder.repositoryServices.RepositoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CartServicesImpl implements CartServices {

    @Autowired
    RepositoryServices repositoryServices;

    @Autowired
    CartRepository cartRepository;


    @Override
    public Cart getCart(String userId) {
        Cart cart = repositoryServices.findOrCreateCart(userId);

        //System.out.println(cart.getCustomerId());
        double totalamount = 0;

        if(cart.getItems().size() > 0) {
            List<CartProduct> list = cart.getItems();

            Iterator<CartProduct> iterator = list.iterator();



            while (iterator.hasNext()) {
                CartProduct cartProduct = iterator.next();
                totalamount += cartProduct.getPrice() * cartProduct.getQuantity();
            }

            cart.setTotalAmount(totalamount);
            repositoryServices.save(cart);
        }

        return cart;
    }

    @Override
    public CartModifiedResponse addItemToCart(String userId, CartProduct cartProduct) {
        Cart cart = repositoryServices.addItem(userId,cartProduct);

        CartModifiedResponse cartModifiedResponse = new CartModifiedResponse();
        cartModifiedResponse.setCart(cart);
        cartModifiedResponse.setResultCode(100);

        return  cartModifiedResponse;
    }

    @Override
    public CartModifiedResponse removeItemFromCart(String userId, String itemId) {

        Cart cart = repositoryServices.removeItem(userId,itemId);

        CartModifiedResponse response = new CartModifiedResponse();

        response.setCart(cart);
        response.setResultCode(100);

        return response;


    }

    @Override
    public CartModifiedResponse reduceItemFromCart(String userId, String productId) {

        Cart cart = repositoryServices.reduceItem(userId,productId);
        CartModifiedResponse response = new CartModifiedResponse();

        response.setCart(cart);
        response.setResultCode(100);

        return  response;
    }

    @Override
    public Boolean swapCarts(String userId, String guestId) {

        Cart userCart = repositoryServices.findOrCreateCart(userId);

        Cart guestCart = repositoryServices.findOrCreateCart(guestId);

        if(guestCart.getItems().size() >0){
            List<CartProduct> cartProducts  = guestCart.getItems();

            Iterator<CartProduct> iterator = cartProducts.iterator();

            while(iterator.hasNext()){
                CartProduct cartProduct = iterator.next();
                Iterator<CartProduct> userCartIterator = userCart.getItems().iterator();

                int flag =0;
                while(userCartIterator.hasNext()){
                    CartProduct userProduct = userCartIterator.next();

                    if(cartProduct.getProductId().equals(userProduct.getProductId()) && cartProduct.getMerchantId().equals(userProduct.getMerchantId())){
                        userProduct.setQuantity(userProduct.getQuantity() + cartProduct.getQuantity());
                        flag =1;
                        break;
                    }
                }

                if(flag == 0){
                    userCart.getItems().add(cartProduct);
                }


            }

            cartRepository.save(userCart);
            cartRepository.delete(guestCart);

            return true;
        }

        else if(guestCart.getItems().size() == 0){
            cartRepository.delete(guestCart);
        }


        return true;
    }


}
