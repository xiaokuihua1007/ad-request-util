package org.classmatechen.baidu;

import java.lang.reflect.Method;
import java.util.Objects;

import org.classmatechen.basic.Response;

import com.baidu.dev2.api.sdk.common.ApiRequestHeader;
import com.baidu.dev2.api.sdk.invoke.ApiException;

public abstract class ReflectBdRequest<P, R> extends BdRequest<P, R> {

    private Method setHeader;
    private Method setBody;
    private int flag = 0;

    private void initIfNecessary(P param) {

        if (flag > 0) {
            return;
        }
        Class<?> cls = getWrapper();
        try {
            setHeader = cls.getMethod("setHeader", ApiRequestHeader.class);
            setBody = cls.getMethod("setBody", param.getClass());
            flag = 1;
        } catch (Exception e) {
            flag = 2;
        }
    }

    @Override
    protected Response<R> todo(ApiRequestHeader header, P param) throws ApiException {

        initIfNecessary(param);

        Object wrapper = null;
        try { wrapper = getWrapper().newInstance(); } catch (Exception e) { }

        boolean flag = false;
        if (Objects.nonNull(wrapper)) {
            try {
                setHeader.invoke(wrapper, header);
                setBody.invoke(wrapper, param);
                flag = true;
            } catch (Exception e) {

            }
        }

        if (flag) {
            return todo(wrapper);
        } else {
            throw new RuntimeException("unsupport reflect baidu request");
        }
    }

    protected abstract Class<?> getWrapper();

    /**
     * @param wrapper 这个 wrapper 对象的实际类型是 protected Class<?> getWrapper(); 的返回值, 进行强转的时候注意一下
     * @param parser
     * @return
     * @throws ApiException
     */
    protected abstract Response<R> todo(Object wrapper) throws ApiException;
}
