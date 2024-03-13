package com.kzvo.ptmbackend.mapper;

import com.kzvo.ptmbackend.api.dto.UserDto;
import com.kzvo.ptmbackend.persistence.entity.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public UserDto mapEntityToDto(UserEntity userEntity){
        return mapper.map(userEntity, UserDto.class);
    }

    public UserEntity mapDtoToEntity(UserDto userDto){
        return mapper.map(userDto, UserEntity.class);
    }

    public List<UserDto> mapListEntityToListDto(List<UserEntity> userEntities){
        return userEntities.stream()
                .map(userEntity -> mapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserEntity> mapListDtoToListEntity(List<UserDto> userDtos){
        return userDtos.stream()
                .map(userDto -> mapper.map(userDto, UserEntity.class))
                .collect(Collectors.toList());
    }

}
