package com.bbiloskursky.service.impl;

import com.bbiloskursky.model.Phone;
import com.bbiloskursky.service.PhoneCatalogRemoteService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;


@Service
public class PhoneCatalogRemoteServiceImpl implements PhoneCatalogRemoteService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${phone.service.url}")
    private String phoneServiceUrl;

    @Override
    public List<Phone> getPhones() {
        Phone[] phones = restTemplate.getForObject(phoneServiceUrl, Phone[].class);
        return Arrays.asList(phones);
    }

    @Override
    public List<Phone> getPhonesByIds(List<String> ids) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(phoneServiceUrl).
                queryParam("ids", StringUtils.join(ids, ','));
        Phone[] phones = restTemplate.getForObject(builder.toUriString(), Phone[].class);
        return Arrays.asList(phones);
    }
}
