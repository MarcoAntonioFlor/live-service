package com.liveshop.liveservice.entrypoint;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.entrypoint.model.LiveRequest;
import com.liveshop.liveservice.gateway.mongo.LiveRepository;
import com.liveshop.liveservice.usecase.SaveLiveUseCase;
import com.liveshop.liveservice.usecase.SaveLiveUseCase.Input;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
            request.getLiveAt(),
            request.getInfluencers(),
            request.getBrands(),
            request.getTopics(),
            request.isPrivate())
    );
  }

}
