package org.classmatechen.basic.group;

import org.classmatechen.basic.Context;

import lombok.Getter;

@Getter
public class GroupFail<P> {

    private final Context context;
    private final P param;
    private final String error;

    public GroupFail(Param<P> param, String error) {
        this.context = param.getContext();
        this.param = param.getParam();
        this.error = error;
    }
}
