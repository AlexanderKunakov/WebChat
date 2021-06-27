package spring.webchat.model.dao;

import spring.webchat.model.entity.User;

public interface UserDao {

  void add(User user);

  User getById(int id);

  User getByUsername(String username);

  void delete(int id);

}
