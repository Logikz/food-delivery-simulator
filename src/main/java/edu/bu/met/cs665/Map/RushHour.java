package edu.bu.met.cs665.Map;

/**
 * A strategy interface to allow for multiple classes to implement.  This could allow for different
 * rush hour strategies for different regions or cities, since the rush hour times might be different.
 */
public interface RushHour {

  /**
   * Takes in an integer, which could represent the time in seconds since epoc to try to understand
   * if the time represents being in rush hour.
   * @param time Time in seconds or ticks.
   * @return A boolean if the time represents rush hour.
   */
  Boolean isRushHour(int time);
}
