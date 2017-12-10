package de.struktuhr.oauthresource.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String token = httpServletRequest.getHeader(RequestContext.AUTHORIZATION_HEADER);

        System.out.println("Filter token = " + token);

        RequestContext.getContext().setAuthToken(token);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
