package edu.bu.met.cs665.Structures;

import java.awt.Point;

/**
 * A residence to deliver an order.
 */
public class Residence extends Structure {

  /**
   * Constructor
   * @param location location of the residence
   */
  public Residence(Point location) {
    this.location = location;
  }

  @Override
  public String getMapCode() {
    return "R";
  }

  @Override
  public String toString() {
    return "Residence";
  }
}
