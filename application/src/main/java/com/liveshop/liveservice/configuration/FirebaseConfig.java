package com.liveshop.liveservice.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {

  private final SecurityProperties secProps;

  @Primary
  @Bean
  @SneakyThrows
  public FirebaseApp loadFireBaseApp() {
    GoogleCredentials googleCredentials = GoogleCredentials
        .fromStream(new ClassPathResource("firebase_config.json").getInputStream());

    FirebaseOptions firebaseOptions = FirebaseOptions
        .builder()
        .setCredentials(googleCredentials)
        .build();

    return FirebaseApp.initializeApp(firebaseOptions);
  }

  @Bean
  @DependsOn(value = "loadFireBaseApp")
  public FirebaseMessaging loadFirebaseMessaging() {
    return FirebaseMessaging.getInstance();
  }
}
