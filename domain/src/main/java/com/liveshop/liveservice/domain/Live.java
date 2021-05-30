package com.liveshop.liveservice.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Live {

  private String id;
  private LocalDate createAt;
  private LocalDate updateAt;
  private LocalDateTime liveAt;
  private List<String> influencers;
  private List<String> brands;
  private List<String> topics;
  private boolean isPrivate;

}
