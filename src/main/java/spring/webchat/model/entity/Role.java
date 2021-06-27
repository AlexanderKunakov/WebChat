package spring.webchat.model.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "roles")
public class Role {

  public Role() {
  }

  public Role(int id, String role) {
    this.id = id;
    this.role = role;
  }

  public Role(String role) {
    this.role = role;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "role")
  private String role;

  public Set<SimpleGrantedAuthority> simpleGrantedAuthority() {
    Set<SimpleGrantedAuthority> set = new HashSet<>();
    set.add(new SimpleGrantedAuthority(role));
    return set;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", role='" + role + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role1 = (Role) o;
    return id == role1.id && role.equals(role1.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, role);
  }

}
