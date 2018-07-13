package edu.bu.met.cs665.Vehicles;

import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Mission.DestinationMission;
import edu.bu.met.cs665.Mission.Idle;
import edu.bu.met.cs665.Mission.VehicleMission;
import edu.bu.met.cs665.Payment.Order;
import edu.bu.met.cs665.Payment.OrderStatus;
import edu.bu.met.cs665.Structures.Residence;
import edu.bu.met.cs665.Structures.Shop;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Vehicle implements Observer {

  List<Good> goods;
  Order order;
  Point location;
  ObjectMap objectMap;
  VehicleMission deliveryMission;
  Structure destination;


  @Override
  public void update(Observable o, Object arg) {
    if(arg instanceof Order){
      this.order = (Order) arg;
      this.deliveryMission = new DestinationMission(this.order.getDeliveryAddress());
      this.order.setStatus(OrderStatus.IN_TRANSIT);
    } else if(arg == null){
      if(this.order.getDeliveryAddress().equals(this.location)){
        // deliver the order
        this.order.setStatus(OrderStatus.DELIVERED);
        this.deliveryMission = new Idle();
        //todo notify the manager order was delivered
      } else if(destination.getLocation().equals(this.location)){
        this.goods.add(((Shop) destination).getGood());

        //figure out our next location
        // if we have everything, move toward our delivery point
        if(goods.equals(this.order.getGoods())){
          this.destination = new Residence(this.order.getDeliveryAddress());
          this.deliveryMission = new DestinationMission(this.destination.getLocation());
        } else {
          //find the closest shop to a good we need and move there.
        }
      }
      else {
        this.deliveryMission.move(this.location, this.objectMap);
      }
    }
  }
  /**
   * List of goods the vehicle can store, some have freezers which can hold cold items
   * @return List of goods this vehicle can store
   */
  abstract List<Class<? extends Good>> canStore();

}
