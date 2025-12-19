package org.classmatechen.basic.res;

import org.classmatechen.basic.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseImpl<R> implements Response<R> {

    private R data;
}
