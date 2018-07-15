package edu.bu.met.cs665;

import edu.bu.met.cs665.Goods.FrozenMeal;
import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Map.Mappable;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Map.RushHour;
import edu.bu.met.cs665.Payment.Order;
import edu.bu.met.cs665.Payment.OrderStatus;
import edu.bu.met.cs665.Structures.Residence;
import edu.bu.met.cs665.Structures.Shop;
import edu.bu.met.cs665.Structures.Structure;
import edu.bu.met.cs665.Vehicles.Vehicle;
import edu.bu.met.cs665.util.MapUtils;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Manages the Delivery application.  Observes orders and vehicles to update order status
 * and assign orders to new vehicles.  Updates vehicles positions as they are reported to the manager.
 */
public class DeliveryManager implements Observer {
  private ObjectMap objectMap;
  private List<Vehicle> vehicles;
  private List<Shop> shops;
  private List<Order> orders;
  private RushHour rushHour;

  /**
   * Constructor
   * @param objectMap Map of the world
   * @param vehicles Vehicles in the map
   * @param shops Shops in the map
   * @param orders Orders to process
   * @param rushHourRules Rules to define when is rush hour
   */
  DeliveryManager(ObjectMap objectMap,
      List<Vehicle> vehicles, List<Shop> shops, List<Order> orders,
      RushHour rushHourRules) {
    this.objectMap = objectMap;
    this.vehicles = vehicles;
    this.shops = shops;
    this.orders = orders;
    this.rushHour = rushHourRules;

    for(Vehicle vehicle: vehicles){
      vehicle.addObserver(this);
    }
  }

  DeliveryManager() {
  }

  /**
   * Starts our application.  Exits when all orders are fulfilled.
   */
  void start(){
    int tickCount = 0;
    boolean startRushHour = false;
    boolean shouldFinish = false;
    while(!orders.isEmpty()){
      try {
        // Check if we need to go into or come out of 'rush hour'
        if(!startRushHour && rushHour.isRushHour(tickCount)){
          System.out.println("==== RUSH HOUR BEGINNING ====");
          startRushHour = true;
        } else if (startRushHour && !rushHour.isRushHour(tickCount)){
          startRushHour = false;
          System.out.println("==== RUSH HOUR ENDING ====");
        }

        // process orders
        this.processOrders(tickCount);

        // move vehicles
        vehicles.forEach(Vehicle::tick);

        // print everything
        printMap(objectMap);
        ++tickCount;
        Thread.sleep(50);
        if(!shouldFinish) {
          Scanner scanner = new Scanner(System.in);
          System.out
              .println("Hit enter for the next tick, or 'c' to continue running the whole program");
          String s = scanner.nextLine();
          if (s != null && s.toLowerCase().equals("c")) {
            shouldFinish = true;
          }
        }
      } catch (InterruptedException e) {
        System.err.println(e.getMessage());
      }
    }
  }

  /**
   * Processes orders by assigning items in the ORDERED state to a vehicle, if possible.  Since some
   * vehicles don't have freezers it's possible for some orders to not be assigned, or if we run out
   * of vehicles to assign orders to.
   * @param time The current time or tick value
   */
  private void processOrders(int time) {
    orders.forEach(order -> System.out.printf("%s(%s)\n", order.getOrderId(), order.getStatus()));
    orders.stream().filter(order -> order.getStatus() == OrderStatus.ORDERED).forEach(order -> allocateOrder(order, vehicles, shops, rushHour, time));
  }

  /**
   * Allocate the order
   * @param order Order to allocate
   * @param time The current time or tick value
   */
  void allocateOrder(Order order, List<Vehicle> vehicles, List<Shop> shops, RushHour rushHour, int time) {
    int shortestDistance = Integer.MAX_VALUE;
    Vehicle deliverer = null;
    List<Good> goods = order.getGoods();
    List<Shop> sortableShops = new ArrayList<>();
    Stack<Structure> destinations = new Stack<>();
    // get a list of vehicles that don't have something assigned to them
    List<Vehicle> validVehicles = vehicles.stream().filter(v -> !v.hasOrder()).collect(Collectors.toList());

    Shop frozenGoodShop = null;

    Structure deliveryStructure = new Residence(order.getDeliveryAddress());

    //push the final destination on the stack
    destinations.push(deliveryStructure);

    // for each good, we want to find out which shops we need to go to as well as which vehicles
    // are delivery candidates.
    for(Good good : goods){
      for(Shop shop : shops){
        if(shop.getGood().getClass() == good.getClass()){
          if(good instanceof FrozenMeal){
            //store the frozen shop since it needs to be the final destination for purpopses of
            //assigning frozen items to a vehicle so it can deliver them safely
            frozenGoodShop = shop;
            //if this is a frozen good shop, push it on the destinations so it's the last stop
            //prior to delivery, this gives us a chance of assigning it to a better driver
            destinations.push(shop);
          } else {
            sortableShops.add(shop);
          }
        }
      }

      for(Vehicle vehicle: vehicles){
        // If the order contains a frozen meal, and it's not rush hour...see if we can assign it
        // to a taxi or freezerless van, if the delivery distance (distance from store to house) is less than 2 miles
        if(good instanceof FrozenMeal && !rushHour.isRushHour(time) && frozenGoodShop != null){
          if(!vehicle.canStore(good) && MapUtils.calculateDistance(frozenGoodShop.getLocation(), deliveryStructure.getLocation()) > 2){
            // this vehicle cannot support a frozen good due to delivery distance being too far
            validVehicles.remove(vehicle);
          }
        } else if(!vehicle.canStore(good)){
          validVehicles.remove(vehicle);
        }
      }
    }

    // calculate which vehicle has the shortest route
    for(Vehicle vehicle: validVehicles){
      int distance = MapUtils.calculateTimeRemaining(vehicle.getLocation(), destinations);
      if(distance < shortestDistance){
        shortestDistance = distance;
        deliverer = vehicle;
      }
    }

    // Assign the order to a driver
    if(deliverer != null) {
      // First we want to optimize the route
      Stack<Shop> tempStack = new Stack<>();
      Point currentLocation = deliverer.getLocation();

      // As we find the closest shop, add it to the stack, and search
      // for the next closest store from that one, such that our stack
      // is in reverse order of directions
      while(!sortableShops.isEmpty()) {
        Shop shop = MapUtils.findClosestShop(currentLocation, sortableShops);
        sortableShops.remove(shop);
        tempStack.push(shop);
        currentLocation = shop.getLocation();
      }

      // pop off the stores to add them in the right order to the destinations
      while(!tempStack.empty()){
        destinations.push(tempStack.pop());
      }

      //assign it
      deliverer.setOrder(order, destinations);
      order.addObserver(this);
    }
  }

  /**
   * Print out the map to the console
   * @param objectMap Map to print
   */
  private void printMap(ObjectMap objectMap) {
    for(int i = 0; i < objectMap.getMap().length; ++i){
      for(int j = 0; j < objectMap.getMap().length; ++j){
        StringBuilder cell = new StringBuilder();
        List<Mappable> objects = objectMap.getMap()[i][j].getObjects();
        for(Mappable object: objects){
          cell.append(object.getMapCode());
        }
        System.out.printf("%10s|", cell);
      }
      System.out.print("\n");
      System.out.println("----------------------------------------------------------------------------------------");
      System.out.println();
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    if(o instanceof Vehicle){
      Point oldLocation = (Point) arg;
      Vehicle vehicle = (Vehicle) o;
      objectMap.getMap()[vehicle.getLocation().x][vehicle.getLocation().y].addObject(vehicle);
      objectMap.getMap()[oldLocation.x][oldLocation.y].removeObject(vehicle);
    } else if (o instanceof Order){
      Order order = (Order) o;
      OrderStatus status = (OrderStatus) arg;
      if(status == OrderStatus.DELIVERED){
        orders.remove(order);
      }
    }

  }
}
