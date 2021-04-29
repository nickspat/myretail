package org.nickspat.myretail.controller;

import org.nickspat.model.Product;
import org.nickspat.myretail.service.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductInfo {
    
    @Autowired
    ProductSearch productSearchService;

    @GetMapping(value="/products/{id}")
    public Product fetchProduct(@PathVariable("id") String id){
        return productSearchService.fetchProduct();
    }
}
