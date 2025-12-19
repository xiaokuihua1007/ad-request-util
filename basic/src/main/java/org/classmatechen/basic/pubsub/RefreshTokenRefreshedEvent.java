package org.classmatechen.basic.pubsub;

import org.classmatechen.basic.Context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenRefreshedEvent extends Event {

    private static final String refresh_token_refreshed = "refresh-token-refreshed";
    private final Context context;
    private final String refreshToken;

    public RefreshTokenRefreshedEvent(Context context, String refreshToken) {
        super(refresh_token_refreshed);
        this.context = context;
        this.refreshToken = refreshToken;
        log.info("refresh token refreshed, {}", context);
    }

    @Override
    public void publish(Listener listener) {

        if (listener instanceof RefreshTokenRefreshedListener) {
            ((RefreshTokenRefreshedListener) listener).onRefreshTokenRefreshed(context, refreshToken);
        }
    }
}
