package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Goods.ChocolateBox;
import edu.bu.met.cs665.Goods.Good;
import java.awt.Point;

/**
 * A type of shop to provide a chocolate box
 */
public class ChocolateBoxShop extends Shop {

  /**
   * Constructor
   *
   * @param location Location of the shop
   */
  public ChocolateBoxShop(Point location) {
    super(location);
  }

  @Override
  public Good getGood() {
    return new ChocolateBox();
  }

  @Override
  public String toString() {
    return "Chocolate box shop";
  }

  @Override
  public String getMapCode() {
    return "CS";
  }
}
