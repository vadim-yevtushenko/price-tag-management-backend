package com.kzvo.ptmbackend.api.controller;


import com.kzvo.ptmbackend.api.dto.UserDto;
import com.kzvo.ptmbackend.mapper.UserMapper;
import com.kzvo.ptmbackend.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credentials")
public class CredentialController extends BaseController{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CredentialService credentialService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam(value = "email") String email,
                                @RequestParam(value = "password") String password){
        return credentialService.login(email, password);
    }

    @PostMapping("/user-registration")
    public ResponseEntity userRegistration(@RequestParam(value = "email") String email,
                                 @RequestParam(value = "password") String password,
                                 @RequestBody UserDto userDto){
        return credentialService.userRegistration(email, password, userMapper.mapDtoToEntity(userDto));
    }

    @PostMapping("/admin-registration")
    public ResponseEntity adminRegistration(@RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password,
                                  @RequestBody UserDto userDto){
        return credentialService.adminRegistration(email, password, userMapper.mapDtoToEntity(userDto));
    }

}
