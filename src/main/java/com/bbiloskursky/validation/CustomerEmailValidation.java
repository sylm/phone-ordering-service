package com.bbiloskursky.validation;

import com.bbiloskursky.model.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerEmailValidation implements ValidationCondition {

    public static final String INVALID_EMAIL_MESSAGE = "Invalid or empty email was provided";

    private String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    @Override
    public Optional<String> validate(Order order) {
        Pattern pat = Pattern.compile(emailRegex);
        String email = order.getCustomer().getEmail();
        boolean matches = pat.matcher(email).matches();
        if (email == null || !matches)
            return Optional.of(INVALID_EMAIL_MESSAGE);
        return Optional.empty();
    }
}
