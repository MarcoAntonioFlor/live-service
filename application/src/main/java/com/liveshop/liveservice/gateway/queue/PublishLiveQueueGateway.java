package com.liveshop.liveservice.gateway.queue;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.liveshop.liveservice.gateway.PublishLiveGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class PublishLiveQueueGateway implements PublishLiveGateway {

  private final FirebaseMessaging firebaseMessaging;
  private final String TOPIC = "new_live";

  public void execute(final String id) {
    final Message message = buildMessage(id);
    try{
      String send = firebaseMessaging.send(message);
      log.info("publish live {}, {}", message, send);
    }catch (FirebaseMessagingException ex){
      log.error("error for publish {} in topic {}", message, TOPIC);
    }
  }

  private Message buildMessage(final String id){
    return Message.builder()
        .setTopic(TOPIC)
        .putData("id", id)
        .build();
  }
}
