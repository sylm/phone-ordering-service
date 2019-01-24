package com.bbiloskursky.service.impl;

import com.bbiloskursky.exception.IllegalOrderException;
import com.bbiloskursky.model.Order;
import com.bbiloskursky.model.Phone;
import com.bbiloskursky.repository.OrderRepository;
import com.bbiloskursky.service.OrderService;
import com.bbiloskursky.service.PhoneCatalogRemoteService;
import com.bbiloskursky.validation.CustomerEmailValidation;
import com.bbiloskursky.validation.PriceValidation;
import com.bbiloskursky.validation.ValidationCondition;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PhoneCatalogRemoteService phoneCatalogService;


    @Autowired
    private ObjectMapper objectMapper;


    public OrderServiceImpl(OrderRepository orderRepository, PhoneCatalogRemoteService phoneCatalogService, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.phoneCatalogService = phoneCatalogService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Order saveOrder(Order order) {
        this.validateOrder(order);
        if (order == null || CollectionUtils.isEmpty(order.getPhones())) {
            throw new IllegalOrderException("Order must not be empty ");
        }
        Long totalPrice = order.getPhones().stream().map(Phone::getPrice).reduce(0L, Long::sum);
        log.info("Total order price: {}", totalPrice);
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    private void validateOrder(Order order) {
        List<ValidationCondition> conditions = new ArrayList<>();

        conditions.add(new PriceValidation(phoneCatalogService));
        conditions.add(new CustomerEmailValidation());
        for (final ValidationCondition condition : conditions) {
            final Optional<String> error = condition.validate(order);
            if (error.isPresent()) {
                throw new IllegalOrderException(error.get());
            }
        }
    }
}
