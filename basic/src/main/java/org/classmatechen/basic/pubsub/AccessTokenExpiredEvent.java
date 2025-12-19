package org.classmatechen.basic.pubsub;

import org.classmatechen.basic.Context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessTokenExpiredEvent extends Event {

    private static final String access_token_expired = "access-token-expired";
    private final Context context;

    public AccessTokenExpiredEvent(Context context) {
        super(access_token_expired);
        this.context = context;
        log.info("access token expired, {}", context);
    }

    @Override
    public void publish(Listener listener) {

        if (listener instanceof AccessTokenExpiredListener) {
            ((AccessTokenExpiredListener) listener).onAccessTokenExpired(context);
        }
    }
}
