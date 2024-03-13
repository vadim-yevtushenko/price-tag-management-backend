package com.kzvo.ptmbackend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto{

    private String firstName;

    private String lastName;

    private List<RoleDto> roles;

}
