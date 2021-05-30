package com.liveshop.liveservice.gateway.mongo;

import com.liveshop.liveservice.gateway.mongo.model.LiveData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LiveRepository extends ReactiveMongoRepository<LiveData, Long> {
}
