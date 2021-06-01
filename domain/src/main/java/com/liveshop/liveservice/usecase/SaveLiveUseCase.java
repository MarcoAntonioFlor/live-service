package com.liveshop.liveservice.usecase;

import com.liveshop.liveservice.domain.Company;
import com.liveshop.liveservice.domain.Image;
import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.domain.Topic;
import com.liveshop.liveservice.gateway.SaveLiveGateway;
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
    return saveLiveGateway.execute(Input.toLive(input));
  }

  @Data
  @AllArgsConstructor(staticName = "of")
  public static class Input{
    private String description;
    private Image image;
    private LocalDateTime liveAt;
    private List<String> presenters;
    private List<Company> companies;
    private List<Topic> topics;
    private boolean isPrivate;
    private boolean isHighlight;

    static Live toLive(final Input input){
      return Live.builder()
          .description(input.getDescription())
          .image(input.getImage())
          .liveAt(input.getLiveAt())
          .presenters(input.getPresenters())
          .companies(input.getCompanies())
          .topics(input.getTopics())
          .isPrivate(input.isPrivate())
          .isHighlight(input.isHighlight())
          .build();
    }
  }
}
