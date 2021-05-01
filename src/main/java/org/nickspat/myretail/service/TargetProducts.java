package org.nickspat.myretail.service;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

/**
 Utility class to pull the product information from Target's product API
 */
@Component
public class TargetProducts {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
        method to invoke target api and get product information
     */
    public Map<String,String> getProductInfo(String productId) throws Exception{
        
        String targetUrl = String.format("https://redsky.target.com/v3/pdp/tcin/%s?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate", productId);
        
        try{
            WebClient webClient2 = WebClient.builder()
                .baseUrl(targetUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
            Mono<String> response = webClient2.get()
                .retrieve()
                .bodyToMono(String.class);
            String targetProdString = response.block();
            if (targetProdString == null){
                return null;
            }

            //Parse the json value
            return parseValues(targetProdString);
        }
        
        catch(WebClientResponseException e){
            // Target api returns JSON body. Parse json body to determine cause of the error. If tcin is not found in response, means it was incorrect product.
            JsonNode productNode = new ObjectMapper().readTree(e.getResponseBodyAsString());
            if (productNode.get("product").get("item").get("tcin") == null){
                logger.info(e.getResponseBodyAsString());
                return null;
            }
                
            throw e;
        } 
         
    }

    /**
        Parase the json response and fetch only the required value such as tcin, product_description and text
     */
    private Map<String,String> parseValues(String targetProductString) throws JsonMappingException, JsonProcessingException{
        Map<String,String> productInfo = new HashMap<String,String>();
        JsonNode productNode = new ObjectMapper().readTree(targetProductString);
        productInfo.put("productId", productNode.get("product").get("item").get("tcin").textValue());
        productInfo.put("title", productNode.get("product").get("item").get("product_description").get("title").textValue());
        return productInfo;
    }
}
