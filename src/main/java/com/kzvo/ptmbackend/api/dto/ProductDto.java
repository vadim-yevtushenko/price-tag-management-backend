package com.kzvo.ptmbackend.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends BaseDto {

    private String name;

    private String description;

    private String category;

    private String provider;

    private double price;

}
