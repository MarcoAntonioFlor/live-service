package com.liveshop.liveservice.gateway.security;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.liveshop.liveservice.gateway.security.model.Credentials;
import com.liveshop.liveservice.gateway.security.model.UserAuthentication;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

  private final SecurityGateway securityGateway;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    verifyToken(request);
    filterChain.doFilter(request, response);
  }

  private void verifyToken(final HttpServletRequest request) throws AuthenticationException {
    try {
      final String bearerToken = securityGateway.getBearerToken(request);

      if(!ObjectUtils.isEmpty(bearerToken)){
        final FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(bearerToken);
        final UsernamePasswordAuthenticationToken authentication = buildAuthentication(firebaseToken, bearerToken);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }else{
        log.error("bearerToken empty");
      }
    }catch (FirebaseAuthException ex){
      log.error("Firebase Exception:: ", ex.getLocalizedMessage());
    }

  }

  private UsernamePasswordAuthenticationToken buildAuthentication(final FirebaseToken firebaseToken, final String bearerToken){
    return new UsernamePasswordAuthenticationToken(
        buildUser(firebaseToken).get(),
        Credentials.builder(firebaseToken, bearerToken).build(), null);
  }

  private Optional<UserAuthentication> buildUser(final FirebaseToken firebaseToken) {
      if(!isEmpty(firebaseToken)){
        return Optional.of(UserAuthentication
            .builder(firebaseToken.getUid())
            .email(firebaseToken.getEmail())
            .name(firebaseToken.getName())
            .isEmailVerified(firebaseToken.isEmailVerified())
            .issuer(firebaseToken.getIssuer())
            .picture(firebaseToken.getPicture())
            .build());
      }
      return Optional.empty();
  }
}
