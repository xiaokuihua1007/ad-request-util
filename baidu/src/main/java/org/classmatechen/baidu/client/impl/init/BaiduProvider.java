package org.classmatechen.baidu.client.impl.init;

import org.classmatechen.baidu.BdContext;
import org.classmatechen.basic.util.Order;

public interface BaiduProvider extends Order {

    Baidu baidu(BdContext context);
}
