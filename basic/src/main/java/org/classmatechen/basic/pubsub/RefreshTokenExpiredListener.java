package org.classmatechen.basic.pubsub;

import org.classmatechen.basic.Context;

public interface RefreshTokenExpiredListener extends Listener {

    void onRefreshTokenExpired(Context context);
}
