package org.classmatechen.tencent;

import org.classmatechen.basic.Context;
import com.tencent.ads.v3.TencentAds;

public interface TxContext extends Context {

    TencentAds getClient();

    Long getClientId();
}
