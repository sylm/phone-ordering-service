package com.bbiloskursky.service;

import com.bbiloskursky.model.Order;

public interface OrderService {

    Order saveOrder(Order order);

    //In case if this going to be reused, better way of validation should be implemented e.g.
    //Separate annotation with this logic in handler, or implement spring Validator and use @Valid in controller
    //Also customer data validation should be implemented
    void validateOrder(Order order);
}
