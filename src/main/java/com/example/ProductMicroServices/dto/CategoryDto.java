package com.example.ProductMicroServices.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private String categoryId;
    private  String name;
    private  String imageURL;
    private  List<String> attributeList;
}
