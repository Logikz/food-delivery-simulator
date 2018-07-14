package edu.bu.met.cs665.Mission;

import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.List;

public abstract class VehicleMission {

  int timeRemaining;
  List<Structure> destinations;
  /**
   * Move according to it's mission
   */
  public abstract Point move(Point location, ObjectMap objectMap);

  public int getTimeRemaining(){
    return this.timeRemaining;
  }

  public Structure getNextDestination(){
    if(this.destinations.isEmpty()){
      return null;
    }
    return this.destinations.get(0);
  }

  public void popDestination(){
    this.destinations.remove(0);
  }
}
