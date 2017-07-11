package me.dev1001.common.concurrent;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An executor which executes tasks one by one using the underlying {@code Executor} executor.
 * <p> Note that this class is thread safe.
 *
 * @author hongzong.li
 */
@ThreadSafe
public class SerialExecutor implements Executor {
  @GuardedBy("lock")
  private final Queue<Runnable> tasks = new ArrayDeque<>();

  private final Executor executor;

  private Runnable active;

  private final ReentrantLock lock = new ReentrantLock();

  public SerialExecutor(Executor executor) {
    this.executor = executor;
  }

  @Override
  public void execute(final Runnable task) {
    checkNotNull(task);
    lock.lock();
    try {
      tasks.offer(new Runnable() {
        public void run() {
          try {
            task.run();
          } finally {
            scheduleNext();
          }
        }
      });
      if (active == null) {
        scheduleNext();
      }
    } finally {
      lock.unlock();
    }
  }

  private void scheduleNext() {
    lock.lock();
    try {
      if ((active = tasks.poll()) != null) {
        executor.execute(active);
      }
    } finally {
      lock.unlock();
    }
  }

}


