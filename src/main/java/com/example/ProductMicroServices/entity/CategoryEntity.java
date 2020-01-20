package com.example.ProductMicroServices.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("Category")
public class CategoryEntity {
   @Id
   private String categoryId;
   private String name;
   private String imageURL;
   private List<String> attributeList;
}
