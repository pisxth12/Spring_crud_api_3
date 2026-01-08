package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return  productRepository.findAll();
    }

    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product update(Long id , Product product){
        return productRepository.findById(id).map(p->{
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
            p.setImage(product.getImage());
            p.setStatus(product.getStatus());
            return productRepository.save(p);
        }).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }




}
