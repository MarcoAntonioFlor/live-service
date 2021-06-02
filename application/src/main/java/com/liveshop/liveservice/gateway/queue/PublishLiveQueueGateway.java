package com.liveshop.liveservice.gateway.queue;

import com.liveshop.liveservice.gateway.PublishLiveGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PublishLiveQueueGateway implements PublishLiveGateway {

  public void execute(final String id){
    log.info("publish live {}", id);
  }

}
