package edu.bu.met.cs665.Structures;

import edu.bu.met.cs665.Goods.Good;
import java.awt.Point;

public class Shop extends Structure{
  private Good good;

  public Shop(Good good, Point location) {
    this.good = good;
    this.location = location;
  }

  public Good getGood() {
    return good;
  }


  @Override
  public String toString() {
    return "Shop{" +
        "good=" + good +
        ", location=" + location +
        '}';
  }

  @Override
  public String getMapCode() {
    return "S";
  }
}
