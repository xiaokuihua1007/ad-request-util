package org.classmatechen.basic;

public interface Request<P, R> {

    Response<R> request(Context context, P param);
}
