package com.liveshop.liveservice.gateway.database;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.gateway.SearchLivesGateway;
import com.liveshop.liveservice.gateway.database.model.LiveData;
import com.liveshop.liveservice.usecase.SearchLivesUseCase.Input;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class SearchLivesDatabaseGateway implements SearchLivesGateway {

  private final ReactiveMongoTemplate liveRepositoryTemplate;

  @Override
  public Flux<Live> execute(final Input input) {
    return liveRepositoryTemplate
        .find(buildQuery(input), LiveData.class)
        .map(LiveData::toLive);
  }

  private Query buildQuery(final Input input){
    Criteria criteria = new Criteria();

    if (isNotEmpty(input.getIds())) {
      criteria = criteria.and("id").in(input.getIds());
    }
    if (isNotEmpty(input.getIsPrivate())) {
      criteria = criteria.and("isPrivate").is(input.getIsPrivate());
    }

    if (isNotEmpty(input.getIsHighlight())) {
      criteria = criteria.and("isHighlight").is(input.getIsHighlight());
    }

    if (isNotEmpty(input.getPublished())) {
      criteria = criteria.and("published").is(input.getPublished());
    }

    if (isNotEmpty(input.getCompanyName())) {
      criteria = criteria.and("companies.companyName").is(input.getCompanyName());
    }

    if (isNotEmpty(input.getPresenter())) {
      criteria = criteria.and("presenters").in(input.getPresenter());
    }

    if (isNotEmpty(input.getTopicName())) {
      criteria = criteria.and("topics.name").is(input.getStatus());
    }

    if (isNotEmpty(input.getStatus())) {
      criteria = criteria.and("status").is(input.getPresenter());
    }

    return new Query(criteria);
  }

}
