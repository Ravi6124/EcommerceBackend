package com.example.Merchant.MicroService.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Table(name="PRODUCTLISTING")
@Entity
public class ProductListingEntity
{
    String productListingId;
    Map< String,String> attributeMap = new HashMap<>();
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

    public Map<String, String> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map<String, String> attributeMap) {
        this.attributeMap = attributeMap;
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
