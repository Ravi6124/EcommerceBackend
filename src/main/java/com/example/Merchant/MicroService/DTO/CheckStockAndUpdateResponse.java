package com.example.Merchant.MicroService.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckStockAndUpdateResponse
{
    boolean status;
    int quantity;
}
