package org.classmatechen.basic.pubsub;

import org.classmatechen.basic.Context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessTokenRefreshedEvent extends Event {

    private static final String access_token_refreshed = "access-token-refreshed";
    private final Context context;
    private final String accessToken;

    public AccessTokenRefreshedEvent(Context context, String accessToken) {
        super(access_token_refreshed);
        this.context = context;
        this.accessToken = accessToken;
        log.info("access token refreshed, {}", context);
    }

    @Override
    public void publish(Listener listener) {

        if (listener instanceof AccessTokenRefreshedListener) {
            ((AccessTokenRefreshedListener) listener).onAccessTokenRefreshed(context, accessToken);
        }
    }
}
