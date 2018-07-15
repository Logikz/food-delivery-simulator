package edu.bu.met.cs665.Mission;

import edu.bu.met.cs665.Map.ObjectMap;
import java.awt.Point;
import java.util.Random;
import java.util.Stack;

/**
 * An implementation of a strategy to idle when the vehicle doesn't have any goods.
 */
public class Idle extends VehicleMission {

  /**
   * Constructor
   */
  public Idle() {
    this.timeRemaining = 0;
    this.destinations = new Stack<>();
  }

  /**
   * Moves randomly to simulate a car driving around
   * @param location Not used
   * @param objectMap Not used
   * @return A new location to move to
   */
  @Override
  public Point move(Point location, ObjectMap objectMap) {
    // move a random direction
    Random random = new Random();
    int newY = location.y;
    int newX = location.x;

    // pick a cardinal direction
    switch(random.nextInt(4)){
      case 0:
        //north
        newY = Math.max(0, location.y - 1);
        break;
      case 1:
        //east
        newX = Math.min(objectMap.getMap().length-1, location.x + 1);
        break;
      case 2:
        //south
        newY = Math.min(objectMap.getMap().length-1, location.y + 1);
        break;
      case 3:
        //west
        newX = Math.max(0, location.x -1);
        break;
    }

    return new Point(newX, newY);
  }
}
