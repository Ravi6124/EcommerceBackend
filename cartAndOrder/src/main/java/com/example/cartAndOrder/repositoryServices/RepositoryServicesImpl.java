package com.example.cartAndOrder.repositoryServices;

import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.exchanges.CartProduct;
import com.example.cartAndOrder.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class RepositoryServicesImpl implements RepositoryServices {

    @Autowired
    CartRepository cartRepository;


    @Override
    public Cart findOrCreateCart(String userId) {
        Optional<Cart> cart = cartRepository.findById(userId);

        if(cart.isPresent()){
            return cart.get();
        }

        Cart cart1 = new Cart();

        cart1.setCustomerId(userId);
        return cartRepository.save(cart1);

    }

    @Override
    public Cart addItem(String userId, CartProduct cartProduct) {

        Cart cart = findOrCreateCart(userId);


        int flag = 0;
        //null check for list
        if(cart.getItems().size() > 0){

            List<CartProduct> list = cart.getItems();
            Iterator<CartProduct> iterator = list.iterator();

            while(iterator.hasNext()){
                CartProduct cartProduct1 = iterator.next();
                if(cartProduct1.getProductId().equals(cartProduct.getProductId())){

                    int qty = cartProduct.getQuantity();
                    cartProduct1.setQuantity(cartProduct1.getQuantity() + qty);



                    cartRepository.save(cart);

                    flag =1;
                    break;
                }
            }


        }

        if(flag == 0){
            cart.getItems().add(cartProduct);
            //updating the price
            cart.setTotalAmount(cart.getTotalAmount() + cartProduct.getPrice());

            cartRepository.save(cart);
        }

        return cart;

    }

    @Override
    public Cart removeItem(String userId, String itemId) {

        Optional<Cart> cart = cartRepository.findById(userId);

        Cart cart1 = cart.get();
        if(cart1.getItems().size() >1) {
            List<CartProduct> list = cart1.getItems();
            Iterator<CartProduct> iterator = list.iterator();

            while (iterator.hasNext()) {
                CartProduct cartProduct = iterator.next();
                if (itemId.equals(cartProduct.getProductId())) {
                    cart1.setTotalAmount(cart1.getTotalAmount() - cartProduct.getQuantity()*cartProduct.getPrice());
                    list.remove(cartProduct);
                }
            }
        }
        else if(cart1.getItems().size() == 1){
            cart1.setItems(new ArrayList<CartProduct>());
            cart1.setTotalAmount(0);
        }

        return cartRepository.save(cart1);
    }

    @Override
    public Cart reduceItem(String userId, String productId) {

        Optional<Cart> cart = cartRepository.findById(userId);
        Cart cart1 = cart.get();

        if(cart1.getItems().size()!=0) {

            List<CartProduct> list = cart1.getItems();

            Iterator<CartProduct> iterator = list.iterator();

            while (iterator.hasNext()) {

                CartProduct cartProduct = iterator.next();

                if (productId.equals(cartProduct.getProductId())) {

                    if (cartProduct.getQuantity() == 1) {
                        cart1.setTotalAmount(cart1.getTotalAmount()-cartProduct.getPrice());
                        list.remove(cartProduct);
                        break;

                    }

                    cartProduct.setQuantity(cartProduct.getQuantity() - 1);
                    cart1.setTotalAmount(cart1.getTotalAmount()-cartProduct.getPrice());

                }
            }
        }

        return cartRepository.save(cart1);

    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
