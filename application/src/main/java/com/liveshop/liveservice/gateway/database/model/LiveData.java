package com.liveshop.liveservice.gateway.database.model;

import com.liveshop.liveservice.domain.Company;
import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.domain.Topic;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

  @CreatedDate
  private LocalDate createAt;
  @LastModifiedDate
  private LocalDate updateAt;
  private LocalDateTime liveAt;
  private List<String> presenters;
  private List<CompanyData> companies;
  private List<TopicData> topics;
  private boolean isPrivate;
  private boolean isHighlight;
  private boolean published;


  public static LiveData toLiveData(final Live live){
    return LiveData.builder()
        .id(live.getId())
        .liveAt(live.getLiveAt())
        .presenters(live.getPresenters())
        .companies(buidCompaniesData(live.getCompanies()))
        .topics(buidTopicsData(live.getTopics()))
        .isPrivate(live.isPrivate())
        .published(live.isPublished())
        .isHighlight(live.isHighlight())
        .build();
  }

  public static Live toLive(final LiveData live){
    return Live.builder()
        .id(live.getId())
        .liveAt(live.getLiveAt())
        .presenters(live.getPresenters())
        .companies(buidCompanies(live.getCompanies()))
        .topics(buidTopics(live.getTopics()))
        .isPrivate(live.isPrivate())
        .published(live.isPublished())
        .isHighlight(live.isHighlight())
        .build();
  }

  private static List<CompanyData> buidCompaniesData(final List<Company> companies){
    return companies.stream()
        .map(CompanyData::toCompanyData)
        .collect(Collectors.toList());
  }

  private static List<TopicData> buidTopicsData(final List<Topic> topics){
    return topics.stream()
        .map(TopicData::toTopicData)
        .collect(Collectors.toList());
  }

  private static List<Company> buidCompanies(final List<CompanyData> companies){
    return companies.stream()
        .map(CompanyData::toCompany)
        .collect(Collectors.toList());
  }

  private static List<Topic> buidTopics(final List<TopicData> topics){
    return topics.stream()
        .map(TopicData::toTopic)
        .collect(Collectors.toList());
  }

}
