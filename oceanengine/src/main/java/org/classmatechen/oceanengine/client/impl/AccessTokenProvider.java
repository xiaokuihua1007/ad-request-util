package org.classmatechen.oceanengine.client.impl;

import org.classmatechen.basic.util.Order;
import org.classmatechen.oceanengine.DyContext;

public interface AccessTokenProvider extends Order {

    String accessToken(DyContext context);
}
