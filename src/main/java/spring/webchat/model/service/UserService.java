package spring.webchat.model.service;

import spring.webchat.model.entity.User;

public interface UserService {

  void add(User user);

  User getById(int id);

  User getByUsername(String username);

  void delete(int id);

}
