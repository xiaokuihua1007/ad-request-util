package org.classmatechen.basic.util;

public interface Order extends Comparable<Order> {

    int getOrder();

    /**
     * 数字越小, 越靠前
     */
    default int compareTo(Order order) {
        return getOrder() - order.getOrder();
    }
}
