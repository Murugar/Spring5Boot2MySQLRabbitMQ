package com.iqmsoft.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.iqmsoft.entity.Product;
import com.iqmsoft.form.ProductForm;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {

    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImageUrl());
        return productForm;
    }

}