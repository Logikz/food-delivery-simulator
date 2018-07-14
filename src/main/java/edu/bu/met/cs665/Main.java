package edu.bu.met.cs665;

import edu.bu.met.cs665.Goods.ChocolateBox;
import edu.bu.met.cs665.Goods.Flowers;
import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Goods.HappyMeal;
import edu.bu.met.cs665.Goods.Pizza;
import edu.bu.met.cs665.Goods.RoseBouquet;
import edu.bu.met.cs665.Goods.TVDinner;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Payment.Order;
import edu.bu.met.cs665.Structures.Shop;
import edu.bu.met.cs665.Vehicles.Taxi;
import edu.bu.met.cs665.Vehicles.Van;
import edu.bu.met.cs665.Vehicles.Vehicle;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
  private static final Random RANDOM = new Random();
  private static final List<Good> GOODS_LIST = createGoods();
  private static final int MAP_WIDTH = 10;
  private static final int MAP_HEIGHT = 10;
  private static final List<Point> POINTS = new ArrayList<>();

  private static List<Good> createGoods() {
    List<Good> goods = new LinkedList<>();
    goods.add(new HappyMeal());
    goods.add(new Pizza());
    goods.add(new TVDinner());
    goods.add(new RoseBouquet());
    goods.add(new ChocolateBox());

    return goods;
  }

  /**
   * A main method to run examples. 
   * @param args not used 
   */
  public static void main(String[] args) {
    ObjectMap objectMap = new ObjectMap(MAP_WIDTH);

    List<Order> orders = generateRandomOrders(RANDOM.nextInt(30) + 20);
    List<Shop> stores = generateRandomStores(5, objectMap);
    List<Vehicle> vehicles = generateRandomVehicles(10, objectMap);


    System.out.println("Generated " + orders.size() + " orders");
    System.out.println(orders);
    System.out.println(stores);
    System.out.println(vehicles);

    DeliveryManager manager = new DeliveryManager(objectMap, vehicles, stores, orders);
    manager.start();
  }

  private static List<Vehicle> generateRandomVehicles(int numVehicles, ObjectMap objectMap) {
    List<Vehicle> vehicles = new ArrayList<>();
    for(int i = 0; i < numVehicles; ++i){
      Vehicle vehicle = null;
      if(RANDOM.nextInt(2) == 0){
        vehicle = new Taxi(null, generateUniquePoint(), objectMap, i);
      } else {
        if(RANDOM.nextInt(4) < 2){
          vehicle = new Van(null, generateUniquePoint(), objectMap,true, i);
        } else {
          vehicle = new Van(null, generateUniquePoint(), objectMap,false, i);
        }
      }
      vehicles.add(vehicle);
      objectMap.getMap()[vehicle.getLocation().x][vehicle.getLocation().y].addObject(vehicle);
    }
    return vehicles;
  }

  private static List<Shop> generateRandomStores(int numStores,
      ObjectMap objectMap) {
    List<Shop> stores = new ArrayList<>();
    LinkedList<Good> goods = new LinkedList<>(GOODS_LIST);
    for(int i = 0; i < numStores; ++i){
      Shop shop = new Shop(goods.pop(), generateUniquePoint());
      stores.add(shop);
      objectMap.getMap()[shop.getLocation().x][shop.getLocation().y].addObject(shop);
    }
    return stores;
  }

  private static List<Order> generateRandomOrders(int numOrders) {
    List<Order> orders = new ArrayList<>();

    for(int i = 0; i < numOrders; ++i){
      LinkedList<Good> goods = new LinkedList<>(GOODS_LIST);
      Collections.shuffle(goods);
      List<Good> orderedGoods = new ArrayList<>();
      // Calculate a RANDOM number of items for the order
      for(int j = 0; j < RANDOM.nextInt(goods.size()); ++j){
        orderedGoods.add(goods.pop());
      }

      Boolean birthday = RANDOM.nextBoolean();
      if(birthday){
        if(!orderedGoods.stream().anyMatch(good -> good instanceof ChocolateBox)){
          orderedGoods.add(new ChocolateBox());
        }
        if(!orderedGoods.stream().anyMatch(good -> good instanceof Flowers)){
          orderedGoods.add(new RoseBouquet());
        }
      }
      Point point = generateUniquePoint();

      orders.add(new Order(goods, point));
    }

    return orders;
  }

  private static Point generateUniquePoint() {
    int x = RANDOM.nextInt(MAP_WIDTH);
    int y = RANDOM.nextInt(MAP_HEIGHT);

    Point point = new Point(x, y);

    while(POINTS.contains(point)){
      x = RANDOM.nextInt(MAP_WIDTH);
      y = RANDOM.nextInt(MAP_HEIGHT);

      point = new Point(x, y);
    }

    POINTS.add(point);
    return point;

  }

}
