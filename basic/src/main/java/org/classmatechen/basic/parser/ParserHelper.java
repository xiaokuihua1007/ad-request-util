package org.classmatechen.basic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ParserHelper<T extends ResponseParser> implements ResponseParser {

    private final List<T> parsers = new ArrayList<>();
    private final HashMap<String, T> support = new HashMap<>();
    private final Set<String> unSupport = new HashSet<>();

    @Override
    public boolean support(Object object) {

        String className = object.getClass().getName();

        boolean flag = false;
        if (unSupport.contains(className)) {
            flag = false;
        } else if (Objects.nonNull(support.get(className))) {
            flag = true;
        } else {
            synchronized (this) {
                if (unSupport.contains(className)) {
                    flag = false;
                } else if (Objects.nonNull(support.get(className))) {
                    flag = true;
                } else {
                    try {
                        for (T parser : this.parsers) {
                            if (parser.support(object)) {
                                support.put(className, parser);
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            unSupport.add(className);
                        }
                    } catch (Exception exception) {

                    }
                }
            }
        }
        return flag;
    }

    public void register(T parser) {

        this.parsers.add(parser);
    }

    protected T get(Object object) {

        T parser = support.get(object.getClass().getName());
        if (Objects.isNull(parser)) {
            throw new RuntimeException("can not parse this reponse");
        }
        return parser;
    }
}
