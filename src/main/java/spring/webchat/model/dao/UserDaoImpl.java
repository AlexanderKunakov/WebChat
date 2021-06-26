package spring.webchat.model.dao;

import org.hibernate.SessionFactory;
import spring.webchat.model.entity.User;


public class UserDaoImpl implements UserDao {
  private SessionFactory sessionFactory;

  @Override
  public void add(User user) {

  }

  @Override
  public User get(int id) {
    return sessionFactory.openSession().get(User.class, id);
  }

  public Object get2(int id) {
    return sessionFactory.openSession().get(User.class, id);
  }

  @Override
  public void remove(User user) {

  }
}
