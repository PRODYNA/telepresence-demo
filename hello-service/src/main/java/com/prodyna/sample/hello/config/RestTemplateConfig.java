package com.prodyna.sample.hello.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

@Component
public class RestTemplateConfig {
 
  private final String DEBUG_HEADER = "debug";

  @Bean
  @RequestScope
  public RestTemplate keycloakRestTemplate(HttpServletRequest inReq) {
    // retrieve the debug header from incoming request
    final String debugHeader = inReq.getHeader(DEBUG_HEADER);

    final RestTemplate restTemplate = new RestTemplate();
    // add a header if an incoming debug header exists, only
    if (debugHeader != null && !debugHeader.isEmpty()) {
      // since the header should be added to each outgoing request,
      // add an interceptor that handles this.
      restTemplate.getInterceptors().add(
        (outReq, bytes, clientHttpReqExec) -> {
          outReq.getHeaders().set(
            DEBUG_HEADER, debugHeader
          );
          return clientHttpReqExec.execute(outReq, bytes);
        });
    }
    return restTemplate;
  }
}