package com.iqmsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}