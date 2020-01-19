package com.example.Merchant.MicroService.DTO;

import java.util.HashMap;
import java.util.Map;

public class ProductListingDTO
{
    String productListingId;
    Map< String,String> hm = new HashMap<>();
    String merchantId;
    String productId;
    Double price;
    int quantity;

    public String getProductListingId() {
        return productListingId;
    }

    public void setProductListingId(String productListingId) {
        this.productListingId = productListingId;
    }

    public Map<String, String> getHm() {
        return hm;
    }

    public void setHm(Map<String, String> hm) {
        this.hm = hm;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
