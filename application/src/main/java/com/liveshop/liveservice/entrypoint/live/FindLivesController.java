package com.liveshop.liveservice.entrypoint.live;

import static org.apache.commons.lang3.ObjectUtils.allNull;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.usecase.SearchLivesUseCase;
import com.liveshop.liveservice.usecase.SearchLivesUseCase.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class FindLivesController {

  private final SearchLivesUseCase searchLivesUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<Live> searchLives(
      @RequestParam(value = "ids", required = false) final List<String> ids,
      @RequestParam(value = "is_highlight", required = false) final Boolean isHighlight,
      @RequestParam(value = "is_private", required = false) final Boolean isPrivate,
      @RequestParam(value = "is_published", required = false) final Boolean isPublished,
      @RequestParam(value = "presenter_id", required = false) final String presenterId,
      @RequestParam(value = "topic_name", required = false) final String topicName,
      @RequestParam(value = "company_name", required = false) final String companyName,
      @RequestParam(value = "status", required = false) final String status){

    final Input input = Input.of(
        ids,
        isPublished,
        isHighlight,
        isPrivate,
        presenterId,
        topicName,
        companyName,
        status);

    if(validSearch(input)){
      //exceed the search limit
    }
    return searchLivesUseCase.execute(input);
  }

  private boolean validSearch(final Input input){
    return allNull(
        input.getIds(),
        input.getCompanyName(),
        input.getIsHighlight(),
        input.getIsPrivate(),
        input.getPresenter(),
        input.getPublished(),
        input.getStatus(),
        input.getTopicName()
    );
  }
}
