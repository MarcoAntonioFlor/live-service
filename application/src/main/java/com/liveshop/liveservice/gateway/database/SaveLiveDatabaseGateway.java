package com.liveshop.liveservice.gateway.database;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.gateway.SaveLiveGateway;
import com.liveshop.liveservice.gateway.database.model.LiveData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class SaveLiveDatabaseGateway implements SaveLiveGateway {

  private final LiveRepository liveRepository;

  @Override
  public Mono<Live> execute(final Live live) {
    return liveRepository.save(LiveData.toLiveData(live))
        .map(LiveData::toLive);
  }
}
