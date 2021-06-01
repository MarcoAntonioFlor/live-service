package com.liveshop.liveservice.gateway;

import com.liveshop.liveservice.domain.Live;
import reactor.core.publisher.Mono;

public interface SaveLiveGateway {
  Mono<Live> execute(final Live live);
}

