package edu.bu.met.cs665;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.bu.met.cs665.Goods.ChocolateBox;
import edu.bu.met.cs665.Goods.TVDinner;
import edu.bu.met.cs665.Map.ConsoleRushHourStrategy;
import edu.bu.met.cs665.Payment.Order;
import edu.bu.met.cs665.Payment.OrderStatus;
import edu.bu.met.cs665.Structures.ChocolateBoxShop;
import edu.bu.met.cs665.Structures.Shop;
import edu.bu.met.cs665.Structures.TVDinnerShop;
import edu.bu.met.cs665.Vehicles.Taxi;
import edu.bu.met.cs665.Vehicles.Van;
import edu.bu.met.cs665.Vehicles.Vehicle;
import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class DeliveryManagerTester {

  @Test
  void testOrderAssigning() {
    DeliveryManager manager = new DeliveryManager();

    Order order = new Order(Collections.singletonList(new ChocolateBox()), new Point(0, 0));
    List<Vehicle> vehicles = Arrays.asList(new Van(null, new Point(1, 1),
        null, false, 1), new Taxi(null, new Point(9, 9),
        null, 2));
    List<Shop> shops = Collections.singletonList(new ChocolateBoxShop(new Point(3, 3)));

    assertEquals(order.getStatus(), OrderStatus.ORDERED);

    manager.allocateOrder(order, vehicles, shops, new ConsoleRushHourStrategy(), 0);

    assertTrue(vehicles.get(0).hasOrder());
    assertFalse(vehicles.get(1).hasOrder());
    assertEquals(order.getStatus(), OrderStatus.IN_TRANSIT);
  }
  @Test
  void testOrderAssigningFrozen(){
    DeliveryManager manager = new DeliveryManager();
    List<Shop> shops = Collections.singletonList(new TVDinnerShop(new Point(3, 3)));
    List<Vehicle> vehicles = Arrays.asList(new Van(null, new Point(1, 1),
        null, false, 1), new Taxi(null, new Point(9, 9),
        null, 2), new Van(null, new Point(10, 10), null, true, 3));

    Order order = new Order(Collections.singletonList(new TVDinner()), new Point(1, 1));
    assertEquals(order.getStatus(), OrderStatus.ORDERED);

    manager.allocateOrder(order, vehicles, shops, new ConsoleRushHourStrategy(), 0);
    assertTrue(vehicles.get(2).hasOrder());
    assertEquals(order.getStatus(), OrderStatus.IN_TRANSIT);

    // Make the location close to the store so we can assign a different vehicle
    vehicles = Arrays.asList(new Van(null, new Point(1, 1),
        null, false, 1), new Taxi(null, new Point(9, 9),
        null, 2), new Van(null, new Point(10, 10), null, true, 3));

    order = new Order(Collections.singletonList(new TVDinner()), new Point(3, 4));
    assertEquals(order.getStatus(), OrderStatus.ORDERED);

    manager.allocateOrder(order, vehicles, shops, new ConsoleRushHourStrategy(), 0);
    assertTrue(vehicles.get(0).hasOrder());
    assertEquals(order.getStatus(), OrderStatus.IN_TRANSIT);

    // It's rush hour, so assign it to the freezer van instead
    vehicles = Arrays.asList(new Van(null, new Point(1, 1),
        null, false, 1), new Taxi(null, new Point(9, 9),
        null, 2), new Van(null, new Point(10, 10), null, true, 3));

    order = new Order(Collections.singletonList(new TVDinner()), new Point(3, 4));
    assertEquals(order.getStatus(), OrderStatus.ORDERED);

    manager.allocateOrder(order, vehicles, shops, new ConsoleRushHourStrategy(), 30);
    assertTrue(vehicles.get(2).hasOrder());
    assertEquals(order.getStatus(), OrderStatus.IN_TRANSIT);
  }

}
