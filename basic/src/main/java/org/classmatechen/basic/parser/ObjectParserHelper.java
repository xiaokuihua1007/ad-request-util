package org.classmatechen.basic.parser;

import org.classmatechen.basic.Response;

public class ObjectParserHelper extends ParserHelper<ObjectResponseParser> implements ObjectResponseParser {

    @Override
    public <R> Response<R> parse(Object object, Class<R> cls) {
        return get(object).parse(object, cls);
    }
}
