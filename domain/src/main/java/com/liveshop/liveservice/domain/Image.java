package com.liveshop.liveservice.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Image {
  private String type;
  private String description;
  private String url;
}
