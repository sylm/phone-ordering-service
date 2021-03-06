package com.bbiloskursky.service;

import com.bbiloskursky.exception.IllegalOrderException;
import com.bbiloskursky.model.Customer;
import com.bbiloskursky.model.Order;
import com.bbiloskursky.model.Phone;
import com.bbiloskursky.repository.OrderRepository;
import com.bbiloskursky.repository.OrderRepositoryMock;
import com.bbiloskursky.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
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

    @Mock
    private PhoneCatalogRemoteService phoneCatalogService;

    private Phone phone1;
    private Phone phone2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(orderRepository, phoneCatalogService);
        phone1 = new Phone("id1", "name1", "des1", "link1", 500L);
        phone2 = new Phone("id2", "name2", "des2", "link2", 500L);
    }

    @Test
    public void saveOrderTest() {
        when(phoneCatalogService.getPhonesByIds(any())).thenReturn(List.of(phone1, phone2));
        Order order = orderService.saveOrder(getOrder());
        assertEquals(order.getTotalPrice(), Long.valueOf(1000));
    }

    @Test(expected = IllegalOrderException.class)
    public void saveOrderTestWithNull() {
        when(phoneCatalogService.getPhonesByIds(any())).thenReturn(List.of(phone1, phone2));
        orderService.saveOrder(null);
    }

    private Order getOrder() {
        Order order = new Order();
        ;
        order.setPhones(List.of(phone1, phone2));
        order.setCustomer(new Customer("Eo", "test", "email@email.com"));
        return order;
    }
}
