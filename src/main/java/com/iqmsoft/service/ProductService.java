package com.iqmsoft.service;

import java.util.List;

import com.iqmsoft.entity.Product;
import com.iqmsoft.form.ProductForm;

public interface ProductService {

    List<Product> listAll();
    Product getById(Long id);
    Product saveOrUpdate(Product product);
    void delete(Long id);
    Product saveOrUpdateProductForm(ProductForm productForm);
    void sendProductMessage(String id);

}