package de.struktuhr.oauthresource.web;

public class RequestContext {

    public final static String AUTHORIZATION_HEADER = "Authorization";

    private static final ThreadLocal<RequestContext> context = new ThreadLocal<>();

    private String token;

    public static RequestContext getContext() {
        RequestContext ctx = context.get();

        if (ctx == null) {
            ctx = new RequestContext();
            context.set(ctx);
        }

        return ctx;
    }

    public String getAuthToken() {
        return token;
    }

    public void setAuthToken(String token) {
        this.token = token;
    }
}
