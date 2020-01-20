package com.example.ProductMicroServices.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document("Product")
public class ProductEntity {
    @Id
   private String productId;
   private double defaultPrice;
   private int totalStock;
   private String categoryId;
   private String productName;
   private String description;
   private String imageURL;
   private String defaultMerchantId;
}
