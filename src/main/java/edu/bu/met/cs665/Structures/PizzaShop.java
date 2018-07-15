package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Goods.Pizza;
import java.awt.Point;

/**
 * A type of shop to provide warm pizza
 */
public class PizzaShop extends Shop {

  /**
   * Constructor
   *
   * @param location Location of the shop
   */
  public PizzaShop(Point location) {
    super(location);
  }

  @Override
  public Good getGood() {
    return new Pizza();
  }

  @Override
  public String toString() {
    return "Pizza shop";
  }

  @Override
  public String getMapCode() {
    return "PS";
  }
}
