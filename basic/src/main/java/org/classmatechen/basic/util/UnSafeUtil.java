package org.classmatechen.basic.util;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class UnSafeUtil {

    public static <T> T toObject(Object object, Class<T> cls) {
        if (Objects.isNull(object)) {
            return null;
        }
        return (T) object;
    }

    public static <T> List<T> toList(Object object, Class<T> cls) {
        if (Objects.isNull(object)) {
            return null;
        }
        return (List<T>) object;
    }
}
