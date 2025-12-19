package org.classmatechen.baidu.client.impl.refresh;

import org.classmatechen.baidu.BdContext;
import org.classmatechen.basic.util.Order;

public interface RefreshTokenProvider extends Order {

    RefreshDep refreshDep(BdContext context);
}
