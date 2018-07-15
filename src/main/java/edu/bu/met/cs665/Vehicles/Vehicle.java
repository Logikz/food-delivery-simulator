package edu.bu.met.cs665.Vehicles;

import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Map.Mappable;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Mission.DestinationMission;
import edu.bu.met.cs665.Mission.Idle;
import edu.bu.met.cs665.Mission.VehicleMission;
import edu.bu.met.cs665.Payment.Order;
import edu.bu.met.cs665.Payment.OrderStatus;
import edu.bu.met.cs665.Structures.Shop;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;

/**
 * Base class for a vehicle.
 */
public abstract class Vehicle extends Observable implements Mappable {

  int vehicleId;
  List<Good> goods;
  int odometer;
  Point location;
  ObjectMap objectMap;
  VehicleMission deliveryMission;
  private Order order;

  /**
   * Get the current location of the vehicle
   * @return Point
   */
  public Point getLocation() {
    return location;
  }

  /**
   * Sets the order for the vehicle.  This will set the status of the order as being IN_TRANSIT
   * and set the vehicle up to go to the next destination to start picking up goods
   * @param order Order to assign to this vehicle
   * @param destinations A stack of destinations to go to in order
   */
  public void setOrder(Order order, Stack<Structure> destinations){
    this.order = order;
    this.deliveryMission = new DestinationMission(destinations);
    this.order.setStatus(OrderStatus.IN_TRANSIT);
    this.goods = new ArrayList<>();
    System.out.printf("Vehicle %s has been assigned order %s\n", this.vehicleId, order.getOrderId());
    this.odometer = 0;
  }

  /**
   * This class handles the vehicles logic during each 'tick' of the application.  Manages the
   * movement strategy of the vehicle and picks up/delivers goods as needed.
   */
  public void tick() {
    if(this.order != null && this.order.getDeliveryAddress().equals(this.location)){
      // deliver the order
      this.order.setStatus(OrderStatus.DELIVERED);
      this.deliveryMission = new Idle();
      this.order = null;
    } else if(this.deliveryMission instanceof DestinationMission &&
        this.deliveryMission.getNextDestination().getLocation().equals(this.location)){
      this.goods.add(((Shop) this.deliveryMission.getNextDestination()).getGood());
      this.deliveryMission.popDestination();
    }
    else {
      Point newLocation = this.deliveryMission.move(this.location, this.objectMap);
      Point oldLocation = this.location;
      this.location = newLocation;
      setChanged();
      notifyObservers(oldLocation);
      this.odometer++;
    }

    printStatus();
  }

  protected abstract void printStatus();

  /**
   * List of goods the vehicle can store, some have freezers which can hold cold items
   * @return List of goods this vehicle can store
   */
  public abstract Boolean canStore(Good good);

  public List<Good> getGoods() {
    return goods;
  }

  public boolean hasOrder() {
    return this.order != null;
  }

  void printOrderStatus() {
    if(order != null){
      for(Good good : order.getGoods()){
        boolean hasItem = this.goods.stream().anyMatch(g -> g.getClass() == good.getClass());

        String hasItemString = hasItem ? "x" : " ";
        System.out.printf("\t%s(%s)\n", good.toString(), hasItemString);
      }
    }
  }
}
