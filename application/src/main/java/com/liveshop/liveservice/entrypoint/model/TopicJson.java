package com.liveshop.liveservice.entrypoint.model;

import com.liveshop.liveservice.domain.Image;
import com.liveshop.liveservice.domain.Topic;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class TopicJson {
  private String name;
  private String description;
  private List<ImageJson> images;

  public static Topic toTopic(final TopicJson topic){
    return Topic.builder()
        .name(topic.getName())
        .description(topic.getDescription())
        .images(buidImages(topic.getImages()))
        .build();
  }

  private static List<Image> buidImages(final List<ImageJson> images){
    return images.stream()
        .map(ImageJson::toImage)
        .collect(Collectors.toList());
  }
}
