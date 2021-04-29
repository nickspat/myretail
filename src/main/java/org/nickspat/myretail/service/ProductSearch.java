package org.nickspat.myretail.service;

import org.nickspat.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductSearch {

    public Product fetchProduct(){
        Product productObj= new Product();
        Product.Price currentPrice = productObj.new Price();
        productObj.setId("12345");
        productObj.setName("test product");
        currentPrice.setValue(56.28f);
        productObj.setCurrent_price(currentPrice);
        return productObj;
    }
}
