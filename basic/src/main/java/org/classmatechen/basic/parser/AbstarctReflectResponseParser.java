package org.classmatechen.basic.parser;

import java.util.List;

import org.classmatechen.basic.util.ReflectUtil;

public abstract class AbstarctReflectResponseParser implements ResponseParser {

    @Override
    public boolean support(Object object) {

        Class<?> cls = object.getClass();
        if (!cls.getName().startsWith(supportPackage())) {
            return false;
        }
        for (MethodDefinition<?> requiredMethod : requiredMethods()) {
            if (!ReflectUtil.hasMethod(cls, requiredMethod.getMethod())) {
                return false;
            }
        }
        return true;
    }

    protected abstract String supportPackage();

    protected abstract List<MethodDefinition<?>> requiredMethods();
}
