package com.liveshop.liveservice.usecase;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.gateway.PublishLiveGateway;
import com.liveshop.liveservice.gateway.SearchLivesGateway;
import com.liveshop.liveservice.gateway.UpdateLiveGateway;
import com.liveshop.liveservice.usecase.SearchLivesUseCase.Input;
import javax.inject.Named;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Named
@RequiredArgsConstructor
public class PublishLivesUseCase {

  private final UpdateLiveGateway updateLiveGateway;
  private final PublishLiveGateway publishLiveGateway;
  private final SearchLivesGateway searchLivesGateway;

  public Flux<Live> execute(final Input input){
    return searchLivesGateway.execute(input)
        .doOnNext(live -> publishLiveGateway.execute(live.getId()))
        .map(this::updateToPublished)
        .doOnNext(live -> updateLiveGateway.execute(live));
  }

  private Live updateToPublished(final Live live){
    return live.toBuilder()
        .published(true)
        .createAt(live.getCreateAt())
        .build();
  }
}
