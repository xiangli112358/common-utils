package me.dev1001.common.util;

/**
 * @author think on 15/3/2018
 */
@FunctionalInterface
public interface ThrowableFunction<T, R> {

  R apply(T t) throws Exception;

}
