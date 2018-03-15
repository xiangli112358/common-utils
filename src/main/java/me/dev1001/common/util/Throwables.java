package me.dev1001.common.util;

/**
 * @author think on 15/3/2018
 */
public class Throwables {
  private Throwables() {}

  @SuppressWarnings("unchecked")
  public static <E extends Throwable> E sneakyThrow(Throwable e) throws E {
    throw (E) e;
  }

}
