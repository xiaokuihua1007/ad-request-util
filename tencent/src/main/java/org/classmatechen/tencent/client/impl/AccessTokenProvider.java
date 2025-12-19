package org.classmatechen.tencent.client.impl;

import org.classmatechen.basic.util.Order;
import org.classmatechen.tencent.TxContext;

public interface AccessTokenProvider extends Order {

    String accessToken(TxContext context);
}
