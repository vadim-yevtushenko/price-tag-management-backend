package com.kzvo.ptmbackend.mapper;

import com.kzvo.ptmbackend.api.dto.ProductDto;
import com.kzvo.ptmbackend.persistence.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper mapper;

    public ProductDto mapEntityToDto(ProductEntity productEntity){
        return mapper.map(productEntity, ProductDto.class);
    }

    public ProductEntity mapDtoToEntity(ProductDto productDto){
        return mapper.map(productDto, ProductEntity.class);
    }

    public List<ProductDto> mapListEntityToListDto(List<ProductEntity> productEntities){
        return productEntities.stream()
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductEntity> mapListDtoToListEntity(List<ProductDto> productDtos){
        return productDtos.stream()
                .map(productDto -> mapper.map(productDto, ProductEntity.class))
                .collect(Collectors.toList());
    }

}
