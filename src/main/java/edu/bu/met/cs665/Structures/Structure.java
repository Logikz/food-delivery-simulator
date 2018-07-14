package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Map.Mappable;
import java.awt.Point;

public abstract class Structure implements Mappable {
  Point location;


  public Point getLocation() {
    return location;
  }
}
