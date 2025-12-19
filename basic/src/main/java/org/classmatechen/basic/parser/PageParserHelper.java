package org.classmatechen.basic.parser;

import org.classmatechen.basic.res.PageResponse;

public class PageParserHelper extends ParserHelper<PageResponseParser> implements PageResponseParser {

    @Override
    public <R> PageResponse<R> parse(Object object, Class<R> cls) {
        return get(object).parse(object, cls);
    }
}
