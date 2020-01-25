package com.example.cartAndOrder.exchanges.cartExchanges;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class KafkaMailObject {
    String email;
    String message;
}
