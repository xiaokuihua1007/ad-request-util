package org.classmatechen.tencent.client.impl.init;

import org.classmatechen.basic.util.Order;
import org.classmatechen.tencent.TxContext;

public interface TencentProvider extends Order {

    Tencent tencent(TxContext context);
}
