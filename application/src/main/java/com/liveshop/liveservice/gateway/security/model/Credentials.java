package com.liveshop.liveservice.gateway.security.model;

import com.google.firebase.auth.FirebaseToken;
import lombok.Builder;
import lombok.Value;


@Value
@Builder(builderMethodName = "builderMandatory")
public class Credentials {

  public enum CredentialType {
    ID_TOKEN, SESSION
  }

  private CredentialType type;
  private FirebaseToken decodedToken;
  private String idToken;
  private String session;

  public static CredentialsBuilder builder(final FirebaseToken decodedToken, final String idToken) {
    return builderMandatory()
        .decodedToken(decodedToken)
        .idToken(idToken);
  }
}