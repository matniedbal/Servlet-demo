package eu.mrndesign.matned.servletDemo.shop.repository.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductItem {

  private final int id;
  private final Product product;
  @Setter
  private int quantity;

  ProductItem(int id, Product product, int quantity) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
  }

  public void increaseQuantity(int q) {
    quantity += q;
  }

}
