package org.classmatechen.basic.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ReflectUtil {

    /**
     * 
     * @param cls 类型
     * @param str 方法, eg: getData.getPage.getPageSize
     * @return
     */
    public static boolean hasMethod(Class<?> cls, String str) {

        Method method = null;
        String[] methods = str.split("\\.");
        for (String temp : methods) {
            try {
                method = cls.getMethod(temp);
            } catch (NoSuchMethodException | SecurityException exception) {
                method = null;
            }
            if (Objects.isNull(method)) {
                break;
            }
            cls = method.getReturnType();
        }
        return Objects.nonNull(method);
    }

    /**
     *
     * @param object 对象
     * @param str 方法, eg: getData.getPage.getPageSize
     * @return
     */
    public static Object invoke(Object object, String str) {

        if (Objects.isNull(object)) { return null; }

        Method method = null;
        String[] methods = str.split("\\.");
        for (String temp : methods) {
            try {
                method = object.getClass().getMethod(temp);
                if (Objects.isNull(method)) {
                    throw new RuntimeException("no such method");
                }
                object = method.invoke(object);
                if (Objects.isNull(object)) {
                    return null;
                }
            } catch (
                NoSuchMethodException |
                SecurityException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException exception
            ) {

            }
        }
        return object;
    }

    public static void setField(String id, Object object) {

        int index = id.lastIndexOf('.');
        try {
            Field field = Class.forName(id.substring(0, index)).getDeclaredField(id.substring(index + 1));
            field.setAccessible(true);
            field.set(null, object);;
        } catch (Exception e) {

        }
    }
}
