package com.liveshop.liveservice.gateway.database;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.gateway.FindLivesGateway;
import com.liveshop.liveservice.gateway.database.model.LiveData;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class FindLivesDatabaseGateway implements FindLivesGateway {

  private final LiveRepository liveRepository;

  @Override
  public Flux<Live> execute(final List<String> ids) {
    return liveRepository.findAllById(ids)
        .map(LiveData::toLive);
  }
}
