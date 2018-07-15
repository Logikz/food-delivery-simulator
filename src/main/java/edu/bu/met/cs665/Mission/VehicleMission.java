package edu.bu.met.cs665.Mission;

import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.Stack;

/**
 * A strategy class to simulate a vehicle's movement.
 */
public abstract class VehicleMission {

  int timeRemaining;
  Stack<Structure> destinations;
  /**
   * Move according to it's mission
   */
  public abstract Point move(Point location, ObjectMap objectMap);

  /**
   * Get how much time is left.
   * @return Time remaining
   */
  public int getTimeRemaining(){
    return this.timeRemaining;
  }

  /**
   * Peek at the next destination
   * @return Structure of our next destination.
   */
  public Structure getNextDestination(){
    if(this.destinations.isEmpty()){
      return null;
    }
    return this.destinations.peek();
  }

  /**
   * Removes the top destination from the stack
   */
  public void popDestination(){
    this.destinations.pop();
  }
}
