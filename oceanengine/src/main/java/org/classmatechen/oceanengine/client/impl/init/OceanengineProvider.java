package org.classmatechen.oceanengine.client.impl.init;

import org.classmatechen.basic.util.Order;
import org.classmatechen.oceanengine.DyContext;

public interface OceanengineProvider extends Order {

    Oceanengine oceanengine(DyContext context);
}
