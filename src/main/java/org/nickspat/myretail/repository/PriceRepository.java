package org.nickspat.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<Price,String> {
    public Price findByProductId(String productId);
}
