package com.iqmsoft.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqmsoft.Main;
import com.iqmsoft.converter.ProductFormToProduct;
import com.iqmsoft.entity.Product;
import com.iqmsoft.form.ProductForm;
import com.iqmsoft.repository.ProductRepository;
import com.iqmsoft.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;
    private ProductFormToProduct productFormToProduct;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductFormToProduct productFormToProduct,
            RabbitTemplate rabbitTemplate) {
        this.productRepository = productRepository;
        this.productFormToProduct = productFormToProduct;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); // fun with Java 8
        return products;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        Product savedProduct = saveOrUpdate(productFormToProduct.convert(productForm));
        log.info("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }

    @Override
    public void sendProductMessage(String id) {
        Map<String, String> actionmap = new HashMap<>();
        actionmap.put("id", id);
        log.info("Sending the index request through queue message");
        rabbitTemplate.convertAndSend(Main.MESSAGE_QUEUE, actionmap);
    }

}