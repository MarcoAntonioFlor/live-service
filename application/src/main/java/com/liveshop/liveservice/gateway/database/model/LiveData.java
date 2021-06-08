package com.liveshop.liveservice.gateway.database.model;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import com.liveshop.liveservice.domain.Company;
import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.domain.Topic;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
  private String description;
  private ImageData image;

  @CreatedDate
  private LocalDateTime createAt;

  @LastModifiedDate
  private LocalDateTime updateAt;

  private LocalDateTime liveAt;
  private List<String> presenters;
  private List<CompanyData> companies;
  private List<TopicData> topics;
  private boolean isPrivate;
  private boolean isHighlight;

  private boolean published;

  public static LiveData toLiveData(final Live live){
    return ofNullable(live)
        .map(LiveData::buildLiveData)
        .orElse(null);
  }

  private static LiveData buildLiveData(final Live live){
    return LiveData.builder()
        .id(live.getId())
        .description(live.getDescription())
        .image(ImageData.toImageData(live.getImage()))
        .liveAt(live.getLiveAt())
        .createAt(live.getCreateAt())
        .updateAt(live.getUpdateAt())
        .presenters(live.getPresenters())
        .companies(buidCompaniesData(live.getCompanies()))
        .topics(buidTopicsData(live.getTopics()))
        .isPrivate(live.isPrivate())
        .published(live.isPublished())
        .isHighlight(live.isHighlight())
        .build();
  }

  public static Live toLive(final LiveData live){
    return ofNullable(live)
        .map(LiveData::buildLive)
        .orElse(null);
  }

  private static Live buildLive(final LiveData live){
    return Live.builder()
        .id(live.getId())
        .description(live.getDescription())
        .image(ImageData.toImage(live.getImage()))
        .liveAt(live.getLiveAt())
        .updateAt(live.getUpdateAt())
        .createAt(live.getCreateAt())
        .presenters(live.getPresenters())
        .companies(buidCompanies(live.getCompanies()))
        .topics(buidTopics(live.getTopics()))
        .isPrivate(live.isPrivate())
        .published(live.isPublished())
        .isHighlight(live.isHighlight())
        .build();
  }

  private static List<CompanyData> buidCompaniesData(final List<Company> companies){
    return defaultIfNull(companies, new ArrayList<Company>())
        .stream()
        .map(CompanyData::toCompanyData)
        .collect(Collectors.toList());
  }

  private static List<TopicData> buidTopicsData(final List<Topic> topics){
    return defaultIfNull(topics, new ArrayList<Topic>()).stream()
        .map(TopicData::toTopicData)
        .collect(Collectors.toList());
  }

  private static List<Company> buidCompanies(final List<CompanyData> companies){
    return defaultIfNull(companies, new ArrayList<CompanyData>())
        .stream()
        .map(CompanyData::toCompany)
        .collect(Collectors.toList());
  }

  private static List<Topic> buidTopics(final List<TopicData> topics){
    return defaultIfNull(topics, new ArrayList<TopicData>()).stream()
        .map(TopicData::toTopic)
        .collect(Collectors.toList());
  }

}
