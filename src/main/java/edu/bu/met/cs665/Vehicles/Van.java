package edu.bu.met.cs665.Vehicles;

import edu.bu.met.cs665.Goods.Chocolate;
import edu.bu.met.cs665.Goods.Flowers;
import edu.bu.met.cs665.Goods.FrozenMeal;
import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Goods.WarmMeal;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Mission.Idle;
import edu.bu.met.cs665.Payment.Order;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Van extends Vehicle {

  Boolean hasFreezer;

  public Van(List<Good> goods, Order order, Point location, ObjectMap objectMap,
      Structure destination, Boolean hasFreezer) {
    this.goods = goods;
    this.order = order;
    this.location = location;
    this.hasFreezer = hasFreezer;
    this.objectMap = objectMap;
    this.deliveryMission = new Idle();
    this.destination = destination;
  }


  @Override
  public List<Class<? extends Good>> canStore() {
    List<Class<? extends Good>> canStore = new ArrayList<>();
    canStore.add(Flowers.class);
    canStore.add(Chocolate.class);
    canStore.add(WarmMeal.class);
    if (hasFreezer) {
      canStore.add(FrozenMeal.class);
    }
    return canStore;
  }
}
