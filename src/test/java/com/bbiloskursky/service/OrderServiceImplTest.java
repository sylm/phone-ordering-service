package com.bbiloskursky.service;

import com.bbiloskursky.exception.IllegalOrderException;
import com.bbiloskursky.model.Order;
import com.bbiloskursky.model.Phone;
import com.bbiloskursky.repository.OrderRepository;
import com.bbiloskursky.repository.OrderRepositoryMock;
import com.bbiloskursky.service.impl.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    @Spy
    private OrderServiceImpl orderService;

    private OrderRepository orderRepository = new OrderRepositoryMock();

    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(orderRepository, restTemplate, objectMapper);
    }

    @Test
    public void saveOrderTest() {
        Order order = orderService.saveOrder(getOrder());
        assertEquals(order.getTotalPrice(), Long.valueOf(1000));
    }

    @Test(expected = IllegalOrderException.class)
    public void saveOrderTestWithNull() {
        orderService.saveOrder(null);
    }

    private Order getOrder() {
        Order order = new Order();
        Phone phone1 = new Phone("id1", "name1", "des1", "link1", 500L);
        Phone phone2 = new Phone("id2", "name2", "des2", "link2", 500L);
        order.setPhones(List.of(phone1, phone2));
        return order;
    }
}
