package com.liveshop.liveservice.domain;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Topic {
  private String name;
  private String description;
  private List<Image> images;
}
