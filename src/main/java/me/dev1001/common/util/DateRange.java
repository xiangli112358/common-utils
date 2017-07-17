package me.dev1001.common.util;

import javax.annotation.concurrent.Immutable;
import java.util.Date;

/**
 * @author hongzong.li
 */
@Immutable
public class DateRange {
  private final long from;
  private final long to;

  public DateRange(Date from, Date to) {
    this.from = from.getTime();
    this.to = to.getTime();
    if (this.to < this.from) {
      throw new IllegalArgumentException("Invalid date range");
    }
  }

  public Date getFrom() {
    return new Date(from);
  }

  public Date getTo() {
    return new Date(to);
  }

  public boolean inRange(Date date) {
    long millis = date.getTime();
    return millis >= from && millis <= to;
  }
}
