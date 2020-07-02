package eu.mrndesign.matned.servletDemo.shop.repository.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductItem {

  private Product product;

  private int quantity;

  public void increaseQuantity(int q) {
    quantity = quantity + q;
  }

}
