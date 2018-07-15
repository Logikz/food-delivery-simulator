package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Goods.HappyMeal;
import java.awt.Point;

/**
 * A type of shop to provide a warm happy meal
 */
public class HappyMealShop extends Shop {

  /**
   * Constructor
   *
   * @param location Location of the shop
   */
  public HappyMealShop(Point location) {
    super(location);
  }

  @Override
  public Good getGood() {
    return new HappyMeal();
  }

  @Override
  public String toString() {
    return "Happy Meal shop";
  }

  @Override
  public String getMapCode() {
    return "HS";
  }
}
