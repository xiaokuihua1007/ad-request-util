package org.classmatechen.basic.parser;

import lombok.Getter;

@Getter
public class MethodDefinition<T> {

    private final String method;
    private final Class<T> returnType;

    public MethodDefinition(String method) {
        this.method = method;
        this.returnType = null;
    }

    public MethodDefinition(String method, Class<T> returnType) {
        this.method = method;
        this.returnType = returnType;
    }
}
