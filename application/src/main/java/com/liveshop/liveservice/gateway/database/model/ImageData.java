package com.liveshop.liveservice.gateway.database.model;

import com.liveshop.liveservice.domain.Image;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
class ImageData {
  private String type;
  private String description;
  private String url;

  public static ImageData toImageData(final Image image){
    return ImageData.builder()
        .description(image.getDescription())
        .type(image.getType())
        .url(image.getUrl())
        .build();
  }

  public static Image toImage(final ImageData image){
    return Image.builder()
        .description(image.getDescription())
        .type(image.getType())
        .url(image.getUrl())
        .build();
  }
}
