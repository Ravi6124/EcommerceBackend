package com.example.Merchant.MicroService.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckStockResponse
{
    boolean status;
    List<UnavailableStock> unavailableStock;
}
