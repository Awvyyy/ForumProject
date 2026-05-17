package com.example.repppp.mappers;

import com.example.repppp.models.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAll();

    Product findById(@Param("id") Long id);

    int insert(Product product);

    int update(Product product);

    int deleteById(@Param("id") Long id);
}