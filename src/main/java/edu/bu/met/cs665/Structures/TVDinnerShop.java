package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Goods.TVDinner;
import java.awt.Point;

/**
 * A type of shop that provides a Frozen TVDinner
 */
public class TVDinnerShop extends Shop {

  /**
   * Constructor
   *
   * @param location Location of the shop
   */
  public TVDinnerShop(Point location) {
    super(location);
  }

  @Override
  public Good getGood() {
    return new TVDinner();
  }

  @Override
  public String toString() {
    return "Frozen TV Dinner";
  }

  @Override
  public String getMapCode() {
    return "FS";
  }
}
