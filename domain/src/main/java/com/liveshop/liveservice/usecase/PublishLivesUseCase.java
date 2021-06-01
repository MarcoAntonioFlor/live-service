package com.liveshop.liveservice.usecase;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.gateway.FindLivesGateway;
import com.liveshop.liveservice.gateway.PublishLiveGateway;
import com.liveshop.liveservice.gateway.UpdateLiveGateway;
import java.util.List;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Named
@RequiredArgsConstructor
public class PublishLivesUseCase {

  private final UpdateLiveGateway updateLiveGateway;
  private final PublishLiveGateway publishLiveGateway;
  private final FindLivesGateway findLivesGateway;

  public Flux<Live> execute(final Input input){
    return findLivesGateway.execute(input.getIds())
        .doOnNext(live -> publishLiveGateway.execute(live.getId()))
        .map(this::updateToPublished)
        .doOnNext(live -> updateLiveGateway.execute(live));
  }

  private Live updateToPublished(final Live live){
    return live.toBuilder()
        .published(true)
        .build();
  }

  @Data
  @AllArgsConstructor(staticName = "of")
  public static class Input{
    private List<String> ids;
  }

}
