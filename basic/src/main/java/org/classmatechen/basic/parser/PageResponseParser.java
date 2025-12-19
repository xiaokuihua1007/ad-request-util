package org.classmatechen.basic.parser;

import org.classmatechen.basic.res.PageResponse;

public interface PageResponseParser extends ListResponseParser {

    <R> PageResponse<R> parse(Object object, Class<R> cls);
}
