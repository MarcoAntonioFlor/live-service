package com.liveshop.liveservice.entrypoint;

import com.liveshop.liveservice.domain.Company;
import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.domain.Topic;
import com.liveshop.liveservice.entrypoint.model.CompanyRequest;
import com.liveshop.liveservice.entrypoint.model.ImageRequest;
import com.liveshop.liveservice.entrypoint.model.LiveRequest;
import com.liveshop.liveservice.entrypoint.model.TopicRequest;
import com.liveshop.liveservice.usecase.SaveLiveUseCase;
import com.liveshop.liveservice.usecase.SaveLiveUseCase.Input;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LiveController {

  private final SaveLiveUseCase saveLive;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Live> save(@RequestBody @Valid  final LiveRequest request){
    return saveLive.execute(
        Input.of(
            request.getDescription(),
            ImageRequest.toImage(request.getImage()),
            request.getLiveAt(),
            request.getPresenters(),
            buildCompanies(request.getCompanies()),
            buildTopics(request.getTopics()),
            request.isPrivate(),
            request.isHighlight())
    );
  }

  private List<Company> buildCompanies(final List<CompanyRequest> companies){
    return companies.stream().map(CompanyRequest::toCompany).collect(Collectors.toList());
  }

  private List<Topic> buildTopics(final List<TopicRequest> topics){
    return topics.stream().map(TopicRequest::toTopic).collect(Collectors.toList());
  }
}
