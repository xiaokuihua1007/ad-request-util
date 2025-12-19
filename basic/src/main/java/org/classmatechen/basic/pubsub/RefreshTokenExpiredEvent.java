package org.classmatechen.basic.pubsub;

import org.classmatechen.basic.Context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenExpiredEvent extends Event {

    private static final String refresh_token_expired = "refresh-token-expired";
    private final Context context;

    public RefreshTokenExpiredEvent(Context context) {
        super(refresh_token_expired);
        this.context = context;
        log.info("refresh token expired, {}", context);
    }

    @Override
    public void publish(Listener listener) {

        if (listener instanceof RefreshTokenExpiredListener) {
            ((RefreshTokenExpiredListener) listener).onRefreshTokenExpired(context);
        }
    }
}
