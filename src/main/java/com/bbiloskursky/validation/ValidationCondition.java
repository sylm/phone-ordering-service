package com.bbiloskursky.validation;

import com.bbiloskursky.model.Order;

import java.util.Optional;

@FunctionalInterface
public interface ValidationCondition {
    Optional<String> validate(Order order);
}
