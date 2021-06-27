package spring.webchat.model.dao;

import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.omg.CORBA.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.webchat.model.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
  private SessionFactory sessionFactory;

  @Override
  public void add(User user) {
    sessionFactory.getCurrentSession().save(user);
  }

  @Override
  public User getById(int id) {
    return sessionFactory.getCurrentSession().get(User.class, id);
  }

  @Override
  public User getByUsername(String username) {
    return (User) sessionFactory.getCurrentSession()
        .createQuery("from User U where U.username = :username")
        .setParameter("username", username)
        .stream()
        .findFirst()
        .get();
  }

  @SneakyThrows
  @Override
  public void delete(int id) {
    User user = sessionFactory.getCurrentSession().get(User.class, id);
    if (user != null) {
      sessionFactory.getCurrentSession().delete(user);
    } else {
      throw new UnknownUserException();
    }
  }

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}
