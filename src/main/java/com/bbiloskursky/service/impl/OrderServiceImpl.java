package com.bbiloskursky.service.impl;

import com.bbiloskursky.exception.IllegalOrderException;
import com.bbiloskursky.model.Order;
import com.bbiloskursky.model.Phone;
import com.bbiloskursky.repository.OrderRepository;
import com.bbiloskursky.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${phone.service.url}")
    private String phoneServiceUrl;

    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Order saveOrder(Order order) {
        if (order == null || CollectionUtils.isEmpty(order.getPhones())) {
            throw new IllegalOrderException("Order must not be empty ");
        }
        Long totalPrice = order.getPhones().stream().map(Phone::getPrice).reduce(0L, Long::sum);
        log.info("Total order price: {}", totalPrice);
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    //In case if this going to be reused, better way of validation should be implemented e.g.
    //Separate annotation with this logic in handler, or implement spring Validator and use @Valid in controller
    //Also customer data validation should be implemented
    @Override
    public void validateOrder(Order order) {
        var phonesFromCatalog = restTemplate.getForObject(phoneServiceUrl, List.class);
        List<Phone> phonesCatalog = objectMapper.convertValue(phonesFromCatalog, new TypeReference<List<Phone>>() {
        });
        for (Phone phone : order.getPhones()) {
            if (!phonesCatalog.contains(phone)) {
                throw new IllegalOrderException("You provided invalid phone data");
            }
        }
    }
}
