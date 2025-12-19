package org.classmatechen.basic.pubsub;

import org.classmatechen.basic.Context;

public interface AccessTokenRefreshedListener extends Listener {

    void onAccessTokenRefreshed(Context context, String accessToken);
}
