package com.bbiloskursky.validation;


import com.bbiloskursky.model.Customer;
import com.bbiloskursky.model.Order;
import com.bbiloskursky.model.Phone;
import com.bbiloskursky.service.PhoneCatalogRemoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceValidationTest {

    private PriceValidation priceValidation;

    @Mock
    private PhoneCatalogRemoteService phoneCatalogService;

    private Phone phone1;

    private Phone phone2;

    private Phone phone3;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        priceValidation = new PriceValidation(phoneCatalogService);
        phone1 = new Phone("id1", "name1", "des1", "link1", 500L);
        phone2 = new Phone("id2", "name2", "des2", "link2", 500L);
        phone3 = new Phone("id1", "name1", "des1", "link1", 1L);
    }

    @Test
    public void testValidate() {
        when(phoneCatalogService.getPhonesByIds(any())).thenReturn(List.of(phone1, phone2));
        Optional<String> result = priceValidation.validate(getOrder());
        Assert.assertTrue(!result.isPresent());
    }

    @Test
    public void validateNegativeTest() {
        when(phoneCatalogService.getPhonesByIds(any())).thenReturn(List.of(phone3, phone2));
        Optional<String> result = priceValidation.validate(getOrder());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get(), PriceValidation.INVALID_ORDER_MESSAGE);

    }

    private Order getOrder() {
        Order order = new Order();
        order.setPhones(List.of(phone1, phone2));
        order.setCustomer(new Customer("Eo", "test", "email@email.com"));
        return order;
    }
}
