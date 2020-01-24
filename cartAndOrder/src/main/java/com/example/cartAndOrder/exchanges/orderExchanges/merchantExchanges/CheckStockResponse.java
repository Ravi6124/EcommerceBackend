package com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges;


import com.example.cartAndOrder.exchanges.orderExchanges.UnavailableStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckStockResponse {
    boolean status;
    List<UnavailableStock> list;
}
