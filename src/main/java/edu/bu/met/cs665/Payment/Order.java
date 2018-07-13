package edu.bu.met.cs665.Payment;

import edu.bu.met.cs665.Goods.Good;
import java.awt.Point;
import java.util.List;

public class Order {
  private List<Good> goods;
  private Point deliveryAddress;
  private OrderStatus status;

  public Order(List<Good> goods, Point deliveryAddress) {
    this.goods = goods;
    this.deliveryAddress = deliveryAddress;
    this.status = OrderStatus.ORDERED;
  }

  @Override
  public String toString() {
    return "Order{" +
        "goods=" + goods +
        ", deliveryAddress=" + deliveryAddress +
        ", status=" + status +
        '}';
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
  }
}
