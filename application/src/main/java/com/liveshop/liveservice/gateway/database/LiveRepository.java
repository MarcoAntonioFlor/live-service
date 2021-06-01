package com.liveshop.liveservice.gateway.database;

import com.liveshop.liveservice.gateway.database.model.LiveData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LiveRepository extends ReactiveMongoRepository<LiveData, String> {
}
