package org.classmatechen.basic.group;

import org.classmatechen.basic.Context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Param<P> {

    private Context context;
    private P param;
}
