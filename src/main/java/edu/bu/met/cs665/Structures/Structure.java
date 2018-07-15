package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Map.Mappable;
import java.awt.Point;

/**
 * Base class to encapsulate a structure, such as a shop or residence
 */
public abstract class Structure implements Mappable {
  Point location;


  /**
   * Get location of structure
   * @return Location as a point
   */
  public Point getLocation() {
    return location;
  }
}
