package org.classmatechen.basic.parser;

import java.util.Arrays;
import java.util.List;

import org.classmatechen.basic.res.ListResponse;
import org.classmatechen.basic.res.ListResponseImpl;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;

/**
 * 通过反射解析List类型的响应值
 */
public class ReflectListResponseParser extends AbstarctReflectResponseParser implements ListResponseParser {

    private String packageName;
    private MethodDefinition<?> method;

    public ReflectListResponseParser(String packageName, MethodDefinition<?> method) {
        this.packageName = packageName;
        this.method = method;
    }

    @Override
    public <R> ListResponse<R> parse(Object object, Class<R> cls) {

        List<R> list = UnSafeUtil.toList(ReflectUtil.invoke(object, method.getMethod()), cls);
        return new ListResponseImpl<>(list);
    }

    @Override
    public String supportPackage() {
        return packageName;
    }

    @Override
    public List<MethodDefinition<?>> requiredMethods() {
        return Arrays.asList(
            method
        );
    }
}
