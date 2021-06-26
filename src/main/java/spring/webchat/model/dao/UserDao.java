package spring.webchat.model.dao;

import spring.webchat.model.entity.User;

public interface UserDao {

  void add(User user);

  User get(int id);

  void remove(User user);

}
