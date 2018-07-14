package edu.bu.met.cs665.Mission;

import edu.bu.met.cs665.Map.ObjectMap;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Idle extends VehicleMission {

  public Idle() {
    this.timeRemaining = 0;
    this.destinations = new ArrayList<>();
  }

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
