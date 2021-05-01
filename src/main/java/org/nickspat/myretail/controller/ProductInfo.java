package org.nickspat.myretail.controller;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nickspat.myretail.model.Product;
import org.nickspat.myretail.service.ProductSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * ProductInfo class contains restful services to fetch the product details, insert new product and delete product
 */
@RestController
public class ProductInfo {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProductSearch productSearchService;

    @GetMapping(value="/products")
    public String fetchAllProduct(){
        
        return "Unimplemented: Coming soon!!";
    }

    /**
     * Restful API to get product detail such as id, name, price.
     * @param productId
     * @return 
     * @throws Exception
     */
    @GetMapping(value="/products/{id}")
    public ResponseEntity<Object> fetchProduct(@PathVariable("id") String productId) throws Exception{
        
        // Validation for empty and invalid product string for better security.
        if(productId == null || (productId.length()) > 9){
            Map<String,String> me = new HashMap<String,String>();
            me.put("error", "Invalid Product ID");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.convertValue(me, JsonNode.class);
            return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode), HttpStatus.BAD_REQUEST);
           
        }
        try{
            //Main service method to get the product details.
            Product p = productSearchService.fetchProduct(productId);

            //If product was not found, return error
            if (p == null){
                Map<String,String> me = new HashMap<String,String>();
                me.put("error", "ProductId not found");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.convertValue(me, JsonNode.class);
                return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode), HttpStatus.NOT_FOUND);
            
            }

            //Returns success response
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        //Catch all the uncaught exception
        Map<String,String> me = new HashMap<String,String>();
        me.put("error", "Try Again Later");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(me, JsonNode.class);
               
        return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * API to create and persist new product. Unimplemented.
     * @return
     */
    @PostMapping(value="/products")
    public ResponseEntity<String> publishNewProduct(){
        //TODO:UnImplemented
        return new ResponseEntity<>("Unimplemented: Coming soon!!",HttpStatus.OK);
    }

    /**
     * API to delete product. Unimplemented.
     * @param productId
     * @return
     */
    @DeleteMapping(value="/products/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable("id") String productId){
        //TODO:UnImplemented
        return new ResponseEntity<>("Unimplemented: Coming soon!!",HttpStatus.OK);
    }
}
