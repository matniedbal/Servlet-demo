package eu.mrndesign.matned.servletDemo.shop.repository.model.entity;

import eu.mrndesign.matned.servletDemo.shop.repository.model.enumRepo.UserType;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "user_name")
  private String name;

  @Column(name = "user_surname")
  private String surname;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "user_type")
  private UserType userType;


}
