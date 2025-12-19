package org.classmatechen.baidu.parser;

import org.classmatechen.basic.parser.AbstarctReflectResponseParser;

public abstract class AbstarctBdReflectResponseParser extends AbstarctReflectResponseParser {

    @Override
    public String supportPackage() {
        return "com.baidu.dev2";
    }
}
