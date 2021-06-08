package com.liveshop.liveservice.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {

  private final SecurityProperties secProps;

  @Primary
  @Bean
  public void firebaseLoad() {

    try (InputStream inputStream = new ClassPathResource("firebase_config.json").getInputStream()){
      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(inputStream))
          .build();

      if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options);
      }
      System.out.println("Firebase Initialize");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
