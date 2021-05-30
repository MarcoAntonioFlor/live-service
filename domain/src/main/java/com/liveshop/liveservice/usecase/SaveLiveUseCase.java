package com.liveshop.liveservice.usecase;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.gateway.mongo.SaveLiveGateway;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Named
@RequiredArgsConstructor
public class SaveLiveUseCase {

  private final SaveLiveGateway saveLiveGateway;

  public Mono<Live> execute(final Input input){
    return saveLiveGateway.execute(buildLive(input))
        .doFinally(__ -> notifyTopic());
  }

  private void notifyTopic(){
    //TODO notificar tópico para envio de alertas sobre lives
  }

  private Live buildLive(final Input input){
    return Live.builder()
        .influencers(input.getInfluencers())
        .topics(input.getTopics())
        .isPrivate(input.isPrivate())
        .liveAt(input.getLiveAt())
        .build();
  }

  @Data
  @AllArgsConstructor(staticName = "of")
  public static class Input{
    private LocalDateTime liveAt;
    private List<String> influencers; //TODO String to Object
    private List<String> brands; //TODO String to Object
    private List<String> topics; //TODO String to Object
    private boolean isPrivate;
  }
}
