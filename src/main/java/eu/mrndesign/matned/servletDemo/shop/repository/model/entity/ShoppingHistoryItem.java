package eu.mrndesign.matned.servletDemo.shop.repository.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "shopping_history_items")
public class ShoppingHistoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    @Column(name = "order_id")
    private Date orderDate;
    @Column(name = "order_id")
    private int orderPrice;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingHistoryItem")
    private List<ProductItem> orderedItems;



}
