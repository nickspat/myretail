package org.nickspat.myretail.service;

import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.nickspat.myretail.model.Product;
import org.nickspat.myretail.repository.Price;
import org.nickspat.myretail.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductSearch {

    @Autowired
    private PriceRepository repository;

    @Autowired
    private TargetProducts targetProductclient;

    public Product fetchProduct(String productId) throws JsonMappingException, JsonProcessingException{
        Product productObj= new Product();
        Product.ProductPrice currentPrice = productObj.new ProductPrice();
        
        Map<String,String> targetProductInfo = targetProductclient.getProductInfo(productId);
        productObj.setId(targetProductInfo.get("productId"));
        productObj.setName(targetProductInfo.get("title"));
        Price priceObj = repository.findByProductId(productId);
        currentPrice.setValue(priceObj.getValue());
        currentPrice.setCurrencyCode(priceObj.getCurrencyType());
        productObj.setCurrent_price(currentPrice);
        return productObj;
    }

}
