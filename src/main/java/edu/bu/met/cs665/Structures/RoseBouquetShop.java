package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Goods.RoseBouquet;
import java.awt.Point;

/**
 * A type of shop to provide a rose bouquet
 */
public class RoseBouquetShop extends Shop {

  /**
   * Constructor
   *
   * @param location Location of the shop
   */
  public RoseBouquetShop(Point location) {
    super(location);
  }

  @Override
  public Good getGood() {
    return new RoseBouquet();
  }

  @Override
  public String toString() {
    return "Rose bouquet shop";
  }

  @Override
  public String getMapCode() {
    return "RS";
  }
}
