package com.liveshop.liveservice.gateway.mongo;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.gateway.mongo.model.LiveData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class SaveLiveMongoGateway implements SaveLiveGateway{

  private final LiveRepository liveRepository;

  @Override
  public Mono<Live> execute(Live live) {
    return liveRepository.save(LiveData.toLiveData(live))
        .map(LiveData::toLive);
  }
}
