package com.liveshop.liveservice.gateway.database.model;

import com.liveshop.liveservice.domain.Image;
import com.liveshop.liveservice.domain.Topic;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TopicData {
  private String name;
  private String description;
  private List<ImageData> images;

  public static TopicData toTopicData(final Topic topic){
    return TopicData.builder()
        .name(topic.getName())
        .description(topic.getDescription())
        .images(buidImagesData(topic.getImages()))
        .build();
  }

  public static Topic toTopic(final TopicData topic){
    return Topic.builder()
        .name(topic.getName())
        .description(topic.getDescription())
        .images(buidImages(topic.getImages()))
        .build();
  }

  private static List<ImageData> buidImagesData(final List<Image> images){
    return images.stream()
        .map(ImageData::toImageData)
        .collect(Collectors.toList());
  }

  private static List<Image> buidImages(final List<ImageData> images){
    return images.stream()
        .map(ImageData::toImage)
        .collect(Collectors.toList());
  }
}
