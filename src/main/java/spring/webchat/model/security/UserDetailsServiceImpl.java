package spring.webchat.model.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.webchat.model.dao.UserDao;
import spring.webchat.model.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UserDao userDao;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.getByUsername(username);
    return SecurityUser.convertFromUser(user);
  }

  @Autowired
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
