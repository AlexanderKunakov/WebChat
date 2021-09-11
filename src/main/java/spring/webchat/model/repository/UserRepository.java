package spring.webchat.model.repository;

import org.springframework.data.repository.CrudRepository;
import spring.webchat.model.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

  UserEntity findByUsername(String username);

}
