package eu.mrndesign.matned.servletDemo.shop.repository.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "product_items")
public class ProductItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "p_i_id")
  private int id;
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
  @JoinColumn(name = "product_id")
  private Product product;
  @Setter
  @Column(name = "p_i_quantity")
  private int quantity;
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
  @JoinColumn(name = "order_id")
  private ShoppingHistoryItem shoppingHistoryItem;

  public ProductItem(int id, Product product, int quantity) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
  }

  public void increaseQuantity(int q) {
    quantity += q;
  }

}
