package de.struktuhr.oauthresource.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class AuthorizationInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        final String token = RequestContext.getContext().getAuthToken();
        if (token != null && token.length() > 0) {
            final HttpHeaders headers = request.getHeaders();
            headers.add(RequestContext.AUTHORIZATION_HEADER, token);
        }
        return execution.execute(request, body);
    }
}
