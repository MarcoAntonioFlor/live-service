package com.liveshop.liveservice.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class Live {
  private String id;
  private String description;
  private Image image;
  private LocalDateTime liveAt;
  private List<String> presenters;
  private List<Company> companies;
  private List<Topic> topics;
  private boolean isPrivate;
  private boolean isHighlight;
  private boolean published;
}
