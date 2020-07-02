package eu.mrndesign.matned.servletDemo.shop.repository.model.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;
    @Column(name = "product_name")
    private String name;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "product_category")
    private Category category;
    @Column(name = "product_description")
    private String description;
    @Column(name = "product_price")
    private Integer price;
    @Column(name = "product_quantity")
    private Integer quantity;


}