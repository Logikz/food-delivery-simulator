package edu.bu.met.cs665.Mission;

import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Structures.Structure;
import edu.bu.met.cs665.util.MapUtils;
import java.awt.Point;
import java.util.Stack;

/**
 * An implementation of vehicle mission to drive to a particular destination.  Destination mission
 * moves toward the nearest destination.
 */
public class DestinationMission extends VehicleMission {

  /**
   * Constructor
   * @param destinations List of destinations to move toward
   */
  public DestinationMission(Stack<Structure> destinations) {
    this.destinations = destinations;
    this.timeRemaining = -1;
  }

  /**
   * Moves toward the nearest destination
   * @param location Current location of our object.
   * @param objectMap Map of the world, as to not go out of bounds.
   * @return A new point after moving.
   */
  @Override
  public Point move(Point location, ObjectMap objectMap) {
    //move toward destination, doesn't have to be fancy, just go x and then y

    // We could implement a routing strategy if we want to abstract how to move toward the object
    // if we wanted to take into account traffic, or a more sophisticated routing algorithm.
    Point destination = this.destinations.peek().getLocation();
    Point newLocation = location;
    if(location.x < destination.x){
      newLocation = new Point(location.x + 1, location.y);
    } else if(location.x > destination.x){
      newLocation = new Point(location.x - 1, location.y);
    } else if(location.y < destination.y){
      newLocation = new Point(location.x, location.y +1 );
    } else if(location.y > destination.y){
      newLocation = new Point(location.x, location.y - 1 );
    }

    this.timeRemaining = MapUtils.calculateTimeRemaining(newLocation, this.destinations);

    return newLocation;
  }
}
