package com.liveshop.liveservice.gateway.security.propertie;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("security")
@Data
public class SecurityPropertie {

  private boolean allowCredentials;
  private List<String> allowedOrigins;
  private List<String> allowedHeaders;
  private List<String> exposedHeaders;
  private List<String> allowedMethods;
  private List<String> allowedPublicApis;

}
