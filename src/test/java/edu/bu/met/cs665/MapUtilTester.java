package edu.bu.met.cs665;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.bu.met.cs665.Structures.ChocolateBoxShop;
import edu.bu.met.cs665.Structures.Residence;
import edu.bu.met.cs665.Structures.RoseBouquetShop;
import edu.bu.met.cs665.Structures.Shop;
import edu.bu.met.cs665.Structures.Structure;
import edu.bu.met.cs665.util.MapUtils;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class MapUtilTester {

  @Test
  void testDistanceCalculations(){
    Point a = new Point(0, 0);
    Point b = new Point(0, 0);
    assertEquals(0, MapUtils.calculateDistance(a, b));
    assertEquals(0, MapUtils.calculateDistance(b, a));

    b = new Point(0, 9);
    assertEquals(9, MapUtils.calculateDistance(a, b));
    assertEquals(9, MapUtils.calculateDistance(b, a));

    b = new Point(9, 0);
    assertEquals(9, MapUtils.calculateDistance(a, b));
    assertEquals(9, MapUtils.calculateDistance(b, a));

    b = new Point(9, 9);
    assertEquals(18, MapUtils.calculateDistance(a, b));
    assertEquals(18, MapUtils.calculateDistance(b, a));
  }

  @Test
  void testTimeRemaining(){
    List<Structure> destinations = new ArrayList<>();
    destinations.add(new Residence(new Point(0, 0)));

    assertEquals(0, MapUtils.calculateTimeRemaining(new Point(0, 0), destinations));
    assertEquals(1, MapUtils.calculateTimeRemaining(new Point(0, 1), destinations));
    assertEquals(2, MapUtils.calculateTimeRemaining(new Point(1, 1), destinations));

    destinations.add(new Residence(new Point(5, 5)));
    assertEquals(10, MapUtils.calculateTimeRemaining(new Point(0, 0), destinations));
    assertEquals(11, MapUtils.calculateTimeRemaining(new Point(0, 1), destinations));
    assertEquals(12, MapUtils.calculateTimeRemaining(new Point(1, 1), destinations));
  }

  @Test
  void testClosestShop(){
    List<Shop> shops = new ArrayList<>();
    Shop roseShop = new RoseBouquetShop(new Point(0, 0));
    shops.add(roseShop);
    ChocolateBoxShop chocolateBoxShop = new ChocolateBoxShop(new Point(5, 5));
    shops.add(chocolateBoxShop);

    assertEquals(roseShop, MapUtils.findClosestShop(new Point(0, 0), shops));
    assertEquals(roseShop, MapUtils.findClosestShop(new Point(2, 2), shops));
    assertEquals(chocolateBoxShop, MapUtils.findClosestShop(new Point(3, 3), shops));
    assertEquals(chocolateBoxShop, MapUtils.findClosestShop(new Point(7, 7), shops));
  }
}
