package com.example.Search.MicroService.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "productCollection")
@Getter
@Setter
public class SearchEntity
{
    @Id
    @Indexed(name="itemId",type="string")
    String itemId;

    @Indexed(name="productId",type="string")
    String productId;

    @Indexed(name="productName",type="string")
    String productName;

    @Indexed(name="categoryId",type="string")
    String categoryId;

    @Indexed(name="price",type="double")
    Double price;

    @Indexed(name="description",type="text")
    String description;

    @Indexed(name="imageURL",type="string")
    String imageURL;

    @Indexed(name="categoryName",type="string")
    String categoryName;

    @Indexed(name="color",type="text")
    String color;

    @Indexed(name="theme",type="string")
    String theme;

    @Indexed(name="size",type="string")
    String size;


}
