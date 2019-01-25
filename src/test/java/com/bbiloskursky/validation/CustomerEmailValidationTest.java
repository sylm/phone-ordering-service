package com.bbiloskursky.validation;


import com.bbiloskursky.model.Customer;
import com.bbiloskursky.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerEmailValidationTest {

    private CustomerEmailValidation customerEmailValidation = new CustomerEmailValidation();

    @Test
    public void validateTest() {
        Optional<String> result = customerEmailValidation.validate(getOrder("valid@email.com"));
        Assert.assertTrue(!result.isPresent());
    }

    @Test
    public void validateNegativeTest() {
        Optional<String> result = customerEmailValidation.validate(getOrder("nonValid.email.com"));
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get(), CustomerEmailValidation.INVALID_EMAIL_MESSAGE);
    }

    private Order getOrder(String email) {
        Order order = new Order();
        Customer customer = new Customer();
        customer.setEmail(email);
        order.setCustomer(customer);
        return order;
    }
}
