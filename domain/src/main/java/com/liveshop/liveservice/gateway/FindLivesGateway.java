package com.liveshop.liveservice.gateway;

import com.liveshop.liveservice.domain.Live;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindLivesGateway {
  Flux<Live> execute(final List<String> ids);
}

