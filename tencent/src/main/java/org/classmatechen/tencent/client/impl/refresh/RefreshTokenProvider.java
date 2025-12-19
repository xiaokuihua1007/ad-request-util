package org.classmatechen.tencent.client.impl.refresh;

import org.classmatechen.basic.util.Order;
import org.classmatechen.tencent.TxContext;

public interface RefreshTokenProvider extends Order {

    RefreshDep refreshDep(TxContext context);
}
