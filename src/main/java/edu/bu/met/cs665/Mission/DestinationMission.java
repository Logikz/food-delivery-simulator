package edu.bu.met.cs665.Mission;

import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Structures.Structure;
import edu.bu.met.cs665.util.MapUtils;
import java.awt.Point;
import java.util.List;

public class DestinationMission extends VehicleMission {

  public DestinationMission(List<Structure> destinations) {
    this.destinations = destinations;
    this.timeRemaining = -1;
  }

  @Override
  public Point move(Point location, ObjectMap objectMap) {
    //move toward destination, doesn't have to be fancy, just go x and then y
    Point destination = this.destinations.get(0).getLocation();
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
