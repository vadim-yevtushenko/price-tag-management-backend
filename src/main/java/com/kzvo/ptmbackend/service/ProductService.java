package com.kzvo.ptmbackend.service;

import com.kzvo.ptmbackend.persistence.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductEntity> getAllProducts();

    ProductEntity getById(Long id);

    void save(ProductEntity productEntity);

    void deleteById(Long id);

}
