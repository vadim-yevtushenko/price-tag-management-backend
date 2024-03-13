package com.kzvo.ptmbackend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

}
