package com.kzvo.ptmbackend.api.controller;

import com.kzvo.ptmbackend.api.dto.UserDto;
import com.kzvo.ptmbackend.config.security.Administrated;
import com.kzvo.ptmbackend.config.security.Authenticated;
import com.kzvo.ptmbackend.mapper.UserMapper;
import com.kzvo.ptmbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService userService;

    @Authenticated
    @GetMapping
    public List<UserDto> getAllUsers(){
        return mapper.mapListEntityToListDto(userService.getAllUsers());
    }

    @Authenticated
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        return mapper.mapEntityToDto(userService.getById(id));
    }

    @Administrated
    @PostMapping
    public void save(@RequestBody UserDto userDto){
        validateId(userDto.getId(), "Id can not be null");
        userService.save(mapper.mapDtoToEntity(userDto));
    }

    @Administrated
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }
}
