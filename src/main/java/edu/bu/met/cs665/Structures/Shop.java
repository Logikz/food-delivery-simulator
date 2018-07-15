package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Goods.Good;
import java.awt.Point;

/**
 * A shop can provide at most one kind of good for delivery.
 */
public abstract class Shop extends Structure{

  /**
   * Constructor
   * @param location Location of the shop
   */
  public Shop(Point location) {
    this.location = location;
  }

  /**
   * Returns the good this shop provides
   * @return A new good
   */
  public abstract Good getGood();

  @Override
  public abstract String toString();

  @Override
  public abstract String getMapCode();
}
