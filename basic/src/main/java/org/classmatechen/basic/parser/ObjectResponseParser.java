package org.classmatechen.basic.parser;

import org.classmatechen.basic.Response;

public interface ObjectResponseParser extends ResponseParser {

    <R> Response<R> parse(Object object, Class<R> cls);
}
