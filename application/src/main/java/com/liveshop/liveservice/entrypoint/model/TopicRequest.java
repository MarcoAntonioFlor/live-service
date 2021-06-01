package com.liveshop.liveservice.entrypoint.model;

import com.liveshop.liveservice.domain.Image;
import com.liveshop.liveservice.domain.Topic;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class TopicRequest {
  private String name;
  private String description;
  private List<ImageRequest> images;

  public static Topic toTopic(final TopicRequest topic){
    return Topic.builder()
        .name(topic.getName())
        .description(topic.getDescription())
        .images(buidImages(topic.getImages()))
        .build();
  }

  private static List<Image> buidImages(final List<ImageRequest> images){
    return images.stream()
        .map(ImageRequest::toImage)
        .collect(Collectors.toList());
  }
}
