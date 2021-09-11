package spring.webchat.model.service;

import spring.webchat.model.entity.UserEntity;

public interface UserService {

    void add(UserEntity userEntity);

    UserEntity getById(long id);

    UserEntity getByUsername(String username);

  void delete(long id);

}
