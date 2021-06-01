package com.liveshop.liveservice.entrypoint.model;

import com.liveshop.liveservice.domain.Image;
import lombok.Data;

@Data
public class ImageRequest {
  private String type;
  private String description;
  private String url;

  public static Image toImage(final ImageRequest image){
    return Image.builder()
        .description(image.getDescription())
        .type(image.getType())
        .url(image.getUrl())
        .build();
  }
}