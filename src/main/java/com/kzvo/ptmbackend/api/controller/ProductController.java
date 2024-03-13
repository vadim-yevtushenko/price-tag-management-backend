package com.kzvo.ptmbackend.api.controller;

import com.kzvo.ptmbackend.api.dto.ProductDto;
import com.kzvo.ptmbackend.config.security.Administrated;
import com.kzvo.ptmbackend.config.security.Authenticated;
import com.kzvo.ptmbackend.mapper.ProductMapper;
import com.kzvo.ptmbackend.persistence.entity.ProductEntity;
import com.kzvo.ptmbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductService productService;


    @Authenticated
    @GetMapping
    public List<ProductDto> getAllProducts(){
        return mapper.mapListEntityToListDto(productService.getAllProducts());
    }

    @Authenticated
    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id){
        return mapper.mapEntityToDto(productService.getById(id));
    }

    @Administrated
    @PostMapping
    public void save(@RequestBody ProductDto productDto){
        ProductEntity productEntity = mapper.mapDtoToEntity(productDto);
        productService.save(productEntity);
    }

    @Administrated
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.deleteById(id);
    }

}
