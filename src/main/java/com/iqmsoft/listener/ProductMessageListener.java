package com.iqmsoft.listener;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.iqmsoft.entity.Product;
import com.iqmsoft.repository.ProductRepository;


@Component
public class ProductMessageListener {

    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductMessageListener.class);

    public ProductMessageListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

  
    public void receiveMessage(Map<String, String> message) {
        log.info("Received <" + message + ">");
        Long id = Long.valueOf(message.get("id"));
        Product product = productRepository.findById(id).orElse(null);
        product.setMessageReceived(true);
        product.setMessageCount(product.getMessageCount() + 1);

        productRepository.save(product);
        log.info("Message processed...");
    }
    
}