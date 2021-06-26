package spring.webchat.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

//  @Transient
//  transient protected String confirmPassword;
//
//  @ManyToMany
//  @JoinTable(name = "user_role_mapping",
//      joinColumns = @JoinColumn(name = "user_id"),
//      inverseJoinColumns = @JoinColumn(name = "role_id"))
//  transient private Set<Role> roles;

}
