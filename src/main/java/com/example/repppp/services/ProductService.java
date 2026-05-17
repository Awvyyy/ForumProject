package com.example.repppp.services;

import com.example.repppp.mappers.ProductMapper;
import com.example.repppp.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    public List<Product> findAll() {
        return productMapper.findAll();
    }

    public Product getProudctById(Long id){
        Product product = productMapper.findById(id);
        if (product == null){
            throw new RuntimeException("Product not found");
        }

        return product;
    }

    @Transactional
    public Product createProduct(Product product){
        productMapper.insert(product);
        return product;
    }

    @Transactional
    public Product updataProduct (Long id, Product product){
        product.setId(id);

        int updated = productMapper.update(product);
        if (updated == 0){
            throw new RuntimeException("Product not found, id: " + id);

        }
        return product;
    }

    @Transactional
    public void deleteProduct (Long id){
        int deleted = productMapper.deleteById(id);
        if (deleted == 0){
            throw new RuntimeException("Product not found, id: " + id);
        }
    }
}
