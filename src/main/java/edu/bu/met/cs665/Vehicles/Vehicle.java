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

public abstract class Vehicle extends Observable implements Mappable {

  int vehicleId;
  List<Good> goods;
  Order order;
  Point location;
  ObjectMap objectMap;
  VehicleMission deliveryMission;

  public Point getLocation() {
    return location;
  }

  public void setOrder(Order order, List<Structure> destinations){
    this.order = order;
    this.deliveryMission = new DestinationMission(destinations);
    this.order.setStatus(OrderStatus.IN_TRANSIT);
    this.goods = new ArrayList<>();
  }


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
    }
    printStatus();
  }

  protected abstract void printStatus();

  /**
   * List of goods the vehicle can store, some have freezers which can hold cold items
   * @return List of goods this vehicle can store
   */
  public abstract Boolean canStore(Good good);

  public boolean hasOrder() {
    return this.order != null;
  }
}
