package com.liveshop.liveservice.gateway.security.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Data
@Builder(builderMethodName = "builderMandatory")
public class UserAuthentication implements Serializable {

  private String uid;
  private String name;
  private String email;
  private boolean isEmailVerified;
  private String issuer;
  private String picture;


  public static UserAuthenticationBuilder builder(final String uid) {
    return builderMandatory()
        .uid(uid);
  }
}



