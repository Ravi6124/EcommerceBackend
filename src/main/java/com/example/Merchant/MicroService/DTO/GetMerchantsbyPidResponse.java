package com.example.Merchant.MicroService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMerchantsbyPidResponse
{
    private String merchantId;
    private String productId;
    private String merchantName;
    private double merchantRating;
    private double productRating;
    private double cost;
    private String color;
    private String theme;
    private String size;
}
