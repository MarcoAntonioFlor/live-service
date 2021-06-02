package com.liveshop.liveservice.usecase;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.gateway.SearchLivesGateway;
import java.util.List;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import reactor.core.publisher.Flux;

@Named
@RequiredArgsConstructor
public class SearchLivesUseCase {

  private final SearchLivesGateway searchLivesGateway;

  public Flux<Live> execute(final Input input) {
    return searchLivesGateway.execute(input);
  }


  @AllArgsConstructor(staticName = "of")
  @Value
  @Builder
  public static class Input{
    private List<String> ids;
    private Boolean published;
    private Boolean isHighlight;
    private Boolean isPrivate;
    private String presenter;
    private String topicName;
    private String companyName;
    private String status;
  }
}
