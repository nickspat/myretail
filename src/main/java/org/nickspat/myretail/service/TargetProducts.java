package org.nickspat.myretail.service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class TargetProducts {

    public Map<String,String> getProductInfo(String productId) throws JsonMappingException, JsonProcessingException{
        
        String targetUrl = String.format("https://redsky.target.com/v3/pdp/tcin/%s?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate", productId);
        WebClient webClient2 = WebClient.builder()
            .baseUrl(targetUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
        Mono<String> response = webClient2.get()
            .retrieve()
            .bodyToMono(String.class);
        
        return parseValues(response.block());
    }

    private Map<String,String> parseValues(String targetProductString) throws JsonMappingException, JsonProcessingException{
        Map<String,String> productInfo = new HashMap<String,String>();
        JsonNode productNode = new ObjectMapper().readTree(targetProductString);
        productInfo.put("productId", productNode.get("product").get("item").get("tcin").textValue());
        productInfo.put("title", productNode.get("product").get("item").get("product_description").get("title").textValue());
        return productInfo;
    }
}
