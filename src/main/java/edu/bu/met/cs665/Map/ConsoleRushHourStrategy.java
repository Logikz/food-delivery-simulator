package edu.bu.met.cs665.Map;

/**
 * Class to implement a rush hour strategy with a console application using a tick mechanism
 * as a timer
 */
public class ConsoleRushHourStrategy implements RushHour {

  @Override
  public Boolean isRushHour(int time) {
    return time > 25 && time < 50;
  }
}
