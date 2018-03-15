package me.dev1001.common.util;

import java.util.function.Function;

/**
 * @author think on 15/3/2018
 */
public class Functions {
  private Functions() {}

  public static <T, R> Function<T, R> unchecked(ThrowableFunction<T, R> throwableFunction) {
    return t -> {
      try {
        return throwableFunction.apply(t);
      } catch (Throwable e) {
        throw Throwables.sneakyThrow(e);
      }
    };
  }
}
