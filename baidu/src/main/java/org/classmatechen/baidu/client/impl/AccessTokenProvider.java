package org.classmatechen.baidu.client.impl;

import org.classmatechen.baidu.BdContext;
import org.classmatechen.basic.util.Order;

public interface AccessTokenProvider extends Order {

    String accessToken(BdContext context);
}
