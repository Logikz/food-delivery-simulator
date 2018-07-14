package edu.bu.met.cs665.Payment;

import edu.bu.met.cs665.Goods.Good;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.UUID;


public class Order extends Observable {
  private List<Good> goods;
  private Point deliveryAddress;
  private OrderStatus status;
  private List<Object> listeners;
  private UUID orderId;

  public Order(List<Good> goods, Point deliveryAddress) {
    this.goods = goods;
    this.deliveryAddress = deliveryAddress;
    this.status = OrderStatus.ORDERED;
    this.listeners = new ArrayList<>();
    this.orderId = UUID.randomUUID();
  }


  @Override
  public String toString() {
    return "Order{" +
        "goods=" + goods +
        ", deliveryAddress=" + deliveryAddress +
        ", status=" + status +
        '}';
  }

  public UUID getOrderId() {
    return orderId;
  }

  public List<Good> getGoods() {
    return goods;
  }

  public Point getDeliveryAddress() {
    return deliveryAddress;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
    setChanged();
    notifyObservers(this.status);
  }

}
