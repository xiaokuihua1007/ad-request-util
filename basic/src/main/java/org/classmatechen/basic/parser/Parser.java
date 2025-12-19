package org.classmatechen.basic.parser;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.res.ListResponse;
import org.classmatechen.basic.res.PageResponse;

public class Parser {

    private static final Parser self = new Parser();
    private final ObjectParserHelper helper1 = new ObjectParserHelper();
    private final ListParserHelper helper2 = new ListParserHelper();
    private final PageParserHelper helper3 = new PageParserHelper();

    public static void register(ResponseParser parser) {
        if (parser instanceof ObjectResponseParser) {
            self.helper1.register((ObjectResponseParser) parser);
        }
        if (parser instanceof ListResponseParser) {
            self.helper2.register((ListResponseParser) parser);
        }
        if (parser instanceof PageResponseParser) {
            self.helper3.register((PageResponseParser) parser);
        }
    }

    public static <R> Response<R> object(Object object, Class<R> cls) {
        if (self.helper1.support(object)) {
            return self.helper1.parse(object, cls);
        } else {
            throw new RuntimeException("can not parse this reponse");
        }
    }

    public static <R> ListResponse<R> list(Object object, Class<R> cls) {
        if (self.helper2.support(object)) {
            return self.helper2.parse(object, cls);
        } else {
            throw new RuntimeException("can not parse this reponse");
        }
    }

    public static <R> PageResponse<R> page(Object object, Class<R> cls) {
        if (self.helper3.support(object)) {
            return self.helper3.parse(object, cls);
        } else {
            throw new RuntimeException("can not parse this reponse");
        }
    }
}
