package com.liveshop.liveservice.gateway.mongo.model;

import com.liveshop.liveservice.domain.Live;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("live")
@Value
@Builder
public class LiveData {

  @Id
  private String id;
  @CreatedDate
  private LocalDate createAt;
  @LastModifiedDate
  private LocalDate updateAt;
  private LocalDateTime liveAt;
  private List<String> influencers; //TODO String to Object
  private List<String> brands; //TODO String to Object
  private List<String> topics; //TODO String to Object
  private boolean isPrivate;

  public static LiveData toLiveData(final Live live){
    return LiveData.builder()
        .id(live.getId())
        .createAt(live.getCreateAt())
        .updateAt(live.getUpdateAt())
        .influencers(live.getInfluencers())
        .brands(live.getBrands())
        .topics(live.getTopics())
        .isPrivate(live.isPrivate())
        .build();
  }

  public static Live toLive(final LiveData liveData){
    return Live.builder()
        .id(liveData.getId())
        .createAt(liveData.getCreateAt())
        .updateAt(liveData.getUpdateAt())
        .influencers(liveData.getInfluencers())
        .brands(liveData.getBrands())
        .topics(liveData.getTopics())
        .isPrivate(liveData.isPrivate())
        .build();
  }

}
