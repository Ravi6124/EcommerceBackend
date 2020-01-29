package com.example.Merchant.MicroService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSearchProductExtraDetailsResponse
{
    private Double productListingRating;
    private String merchantName;
    private String merchantId;

}
