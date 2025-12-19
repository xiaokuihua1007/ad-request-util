package org.classmatechen.oceanengine.client.impl.refresh;

import org.classmatechen.basic.util.Order;
import org.classmatechen.oceanengine.DyContext;

public interface RefreshTokenProvider extends Order {

    RefreshDep refreshDep(DyContext context);
}
