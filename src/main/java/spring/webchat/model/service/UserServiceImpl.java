package spring.webchat.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.webchat.model.dao.UserDao;
import spring.webchat.model.entity.Role;
import spring.webchat.model.entity.User;

@Service
public class UserServiceImpl implements UserService {
  private UserDao userDao;
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void add(User user) {
    Role defaultRole = new Role(2, "USER");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(defaultRole);
    userDao.add(user);
  }

  @Override
  @Transactional
  public User getById(int id) {
    return userDao.getById(id);
  }

  @Override
  @Transactional
  public User getByUsername(String username) {
    return userDao.getByUsername(username);
  }

  @Override
  @Transactional
  public void delete(int id) {
    userDao.delete(id);
  }

  @Autowired
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
}
