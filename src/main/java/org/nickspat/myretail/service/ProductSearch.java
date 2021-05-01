package org.nickspat.myretail.service;

import java.util.Map;
import org.nickspat.myretail.model.Product;
import org.nickspat.myretail.repository.Price;
import org.nickspat.myretail.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
Service class to get the product info from Target's product api and the mongodb
 */
@Service
public class ProductSearch {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PriceRepository repository;

    @Autowired
    private TargetProducts targetProductclient;

    /**
         method to get product information from target product api and mongodb
     */
    public Product fetchProduct(String productId) throws Exception{
        Product productObj= new Product();
        Product.ProductPrice currentPrice = productObj.new ProductPrice();
        
        //Get product information from target product api
        Map<String,String> targetProductInfo = targetProductclient.getProductInfo(productId);
        if (targetProductInfo == null)
            return null;
        
        productObj.setId(targetProductInfo.get("productId"));
        productObj.setName(targetProductInfo.get("title"));

        //Get product price information from mongodb
        Price priceObj = repository.findByProductId(productId);
        if (priceObj == null){
            return null;
        }
        currentPrice.setValue(priceObj.getValue());
        currentPrice.setCurrencyCode(priceObj.getCurrencyType());
        productObj.setCurrent_price(currentPrice);
        return productObj;
    }

}
