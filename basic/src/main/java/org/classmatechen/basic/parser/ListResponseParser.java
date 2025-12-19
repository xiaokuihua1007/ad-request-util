package org.classmatechen.basic.parser;

import org.classmatechen.basic.res.ListResponse;

public interface ListResponseParser extends ResponseParser {

    <R> ListResponse<R> parse(Object object, Class<R> cls);
}
