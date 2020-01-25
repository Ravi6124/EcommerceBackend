package com.example.cartAndOrder.exchanges.cartExchanges;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SwapCartRequest {

    @NotNull
    String userId;

    @NotNull
    String guestId;
}
