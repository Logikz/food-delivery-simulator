package edu.bu.met.cs665.Mission;

import edu.bu.met.cs665.Map.ObjectMap;
import java.awt.Point;

public class DestinationMission extends VehicleMission {

  public DestinationMission(Point destination) {
    this.destination = destination;
    this.timeRemaining = -1;
  }

  @Override
  public Point move(Point location, ObjectMap objectMap) {
    //move toward destination
    return null;
  }
}
