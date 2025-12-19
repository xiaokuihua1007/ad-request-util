package org.classmatechen.basic.pubsub;

import org.classmatechen.basic.Context;

public interface RefreshTokenRefreshedListener extends Listener {

    void onRefreshTokenRefreshed(Context context, String refreshToken);
}
