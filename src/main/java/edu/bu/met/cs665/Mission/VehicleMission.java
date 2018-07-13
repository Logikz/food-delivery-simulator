package edu.bu.met.cs665.Mission;

import edu.bu.met.cs665.Map.ObjectMap;
import java.awt.Point;

public abstract class VehicleMission {

  int timeRemaining;
  Point destination;
  /**
   * Move according to it's mission
   */
  public abstract Point move(Point location, ObjectMap objectMap);

  int getTimeRemaining(){
    return timeRemaining;
  }
}
