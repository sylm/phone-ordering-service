package com.bbiloskursky.validation;

import com.bbiloskursky.model.Order;
import com.bbiloskursky.model.Phone;
import com.bbiloskursky.service.PhoneCatalogRemoteService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PriceValidation implements ValidationCondition {

    public static final String INVALID_ORDER_MESSAGE = "Invalid order was passed";

    private PhoneCatalogRemoteService phoneCatalogService;

    @Override
    public Optional<String> validate(Order order) {
        List<Phone> phonesFromCatalog = phoneCatalogService.getPhonesByIds(order.getPhones().stream().map(p -> p.getId())
                .collect(Collectors.toList()));
        for (Phone phone : order.getPhones()) {
            Phone catalogPhone = phonesFromCatalog.stream().filter(p -> p.getId().equals(phone.getId())).findAny().get();
            if (catalogPhone == null || !phone.getPrice().equals(catalogPhone.getPrice())) {
                return Optional.of(INVALID_ORDER_MESSAGE);
            }

        }
        return Optional.empty();
    }
}
