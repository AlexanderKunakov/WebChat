package spring.webchat.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.webchat.model.entity.RoleEntity;
import spring.webchat.model.entity.UserEntity;
import spring.webchat.model.enums.RoleEnum;
import spring.webchat.model.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void add(UserEntity userEntity) {
    RoleEntity defaultUserRole = RoleEntity.builder().role(RoleEnum.USER).build();
    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
    userEntity.setRoleEntity(defaultUserRole);

    userRepository.save(userEntity);
  }

  @Override
  @Transactional
  public UserEntity getById(long id) {

    return userRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public UserEntity getByUsername(String username) {

    return userRepository.findByUsername(username);
  }

  @Override
  @Transactional
  public void delete(long id) {

    userRepository.deleteById(id);
  }
}
