package org.classmatechen.basic.pubsub;

import org.classmatechen.basic.Context;

public interface AccessTokenExpiredListener extends Listener {

    void onAccessTokenExpired(Context context);
}
