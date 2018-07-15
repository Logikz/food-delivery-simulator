package edu.bu.met.cs665;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.bu.met.cs665.Goods.ChocolateBox;
import edu.bu.met.cs665.Goods.HappyMeal;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Payment.Order;
import edu.bu.met.cs665.Payment.OrderStatus;
import edu.bu.met.cs665.Structures.ChocolateBoxShop;
import edu.bu.met.cs665.Structures.HappyMealShop;
import edu.bu.met.cs665.Structures.Structure;
import edu.bu.met.cs665.Vehicles.Taxi;
import edu.bu.met.cs665.Vehicles.Vehicle;
import edu.bu.met.cs665.util.MapUtils;
import java.awt.Point;
import java.util.Collections;
import java.util.Stack;
import org.junit.jupiter.api.Test;

class VehicleTester {

  @Test
  void testIdle(){
    ObjectMap objectMap = new ObjectMap(3);
    Vehicle taxi = new Taxi(null, new Point(1, 1), objectMap, 1);

    Point locationBefore = taxi.getLocation();
    taxi.tick();
    Point locationAfter = taxi.getLocation();

    assertNotEquals(locationAfter, locationBefore);
  }

  @Test
  void testDestination(){
    ObjectMap objectMap = new ObjectMap(3);
    Vehicle taxi = new Taxi(null, new Point(1, 1), objectMap, 1);

    Stack<Structure> destinations = new Stack<>();
    destinations.push(new HappyMealShop(new Point(2, 2)));
    taxi.setOrder(new Order(Collections.singletonList(new HappyMeal()), new Point(3, 3)), destinations);
    Point locationBefore = taxi.getLocation();
    taxi.tick();
    Point locationAfter = taxi.getLocation();

    assertTrue(MapUtils.calculateDistance(locationBefore, new Point(2, 2)) >
        MapUtils.calculateDistance(locationAfter, new Point(2, 2)));
  }

  @Test
  void testDelivery(){
    ObjectMap objectMap = new ObjectMap(3);
    Vehicle taxi = new Taxi(null, new Point(1, 1), objectMap, 1);
    Order order = new Order(Collections.singletonList(new ChocolateBox()), new Point(1, 1));
    assertEquals(order.getStatus(), OrderStatus.ORDERED);

    taxi.setOrder(order, null);
    assertEquals(order.getStatus(), OrderStatus.IN_TRANSIT);

    taxi.tick();

    assertFalse(taxi.hasOrder());
    assertEquals(order.getStatus(), OrderStatus.DELIVERED);
  }

  @Test
  void testPickup(){
    ObjectMap objectMap = new ObjectMap(3);
    objectMap.getMap()[1][1].addObject(new ChocolateBoxShop(new Point(1, 1)));
    Vehicle taxi = new Taxi(null, new Point(1, 1), objectMap, 1);
    ChocolateBox box = new ChocolateBox();
    Order order = new Order(Collections.singletonList(box), new Point(3, 3));
    assertEquals(order.getStatus(), OrderStatus.ORDERED);

    Stack<Structure> destinations = new Stack<>();
    destinations.push(new ChocolateBoxShop(new Point(1, 1)));

    taxi.setOrder(order, destinations);
    assertEquals(order.getStatus(), OrderStatus.IN_TRANSIT);
    assertTrue(taxi.getGoods().isEmpty());

    taxi.tick();
    assertEquals(taxi.getGoods().size(), 1);
    assertEquals(taxi.getGoods().get(0).getClass(), box.getClass());
  }

}
