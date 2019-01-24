package com.bbiloskursky.service;

import com.bbiloskursky.model.Phone;

import java.util.List;

public interface PhoneCatalogRemoteService {

    List<Phone> getPhones();

    List<Phone> getPhonesByIds(List<String> ids);
}
