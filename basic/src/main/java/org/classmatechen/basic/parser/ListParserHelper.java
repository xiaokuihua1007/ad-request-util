package org.classmatechen.basic.parser;

import org.classmatechen.basic.res.ListResponse;

public class ListParserHelper extends ParserHelper<ListResponseParser> implements ListResponseParser {

    @Override
    public <R> ListResponse<R> parse(Object object, Class<R> cls) {
        return get(object).parse(object, cls);
    }
}
