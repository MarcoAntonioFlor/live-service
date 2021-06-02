package com.liveshop.liveservice.gateway;

import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.usecase.SearchLivesUseCase.Input;
import reactor.core.publisher.Flux;

public interface SearchLivesGateway {
  Flux<Live> execute(final Input input);
}

