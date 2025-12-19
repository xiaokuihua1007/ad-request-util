package org.classmatechen.tencent.parser;

import org.classmatechen.basic.parser.AbstarctReflectResponseParser;

public abstract class AbstarctTxReflectResponseParser extends AbstarctReflectResponseParser {

    @Override
    public String supportPackage() {
        return "com.tencent.ads.model";
    }
}
