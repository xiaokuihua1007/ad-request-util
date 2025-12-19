package org.classmatechen.basic.group;

import org.classmatechen.basic.Context;

public interface Consumer<P, R> {

    void accept(Context context, P param, R data);
}
