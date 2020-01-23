package com.example.cartAndOrder.feignClient;

import com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges.CheckAndUpdateRequest;
import com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges.CheckAndUpdateResponse;
import feign.Param;
import feign.RequestLine;

public interface MerchantClient {

    @RequestLine("PUT")
    public CheckAndUpdateResponse checkProductStockAndUpdate(CheckAndUpdateRequest checkAndUpdateRequest);

}
