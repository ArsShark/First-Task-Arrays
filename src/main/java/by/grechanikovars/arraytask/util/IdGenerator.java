package by.grechanikovars.arraytask.util;

public class IdGenerator {

  private static long counter = 0L;

  private IdGenerator() {
  }

  public static long generateId() {
    return ++counter;
  }
}