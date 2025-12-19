package org.classmatechen.basic.impl;

/**
 * 如果参数需要校验, 实现这个接口
 */
public interface CheckAble {

    /**
     * @return 如果校验不通过, 返回参数校验失败的原因
     */
    String check();
}
