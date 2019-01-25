package com.bbiloskursky.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String firtsName;

    private String secondName;

    private String email;
}
