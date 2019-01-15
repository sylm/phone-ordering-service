package com.bbiloskursky.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "phones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Phone {

    @Id
    private String id;

    private String name;

    private String description;

    private String photoLink;

    private Long price;

}
