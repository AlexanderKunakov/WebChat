package spring.webchat.model.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
  private static final String DRIVER = "org.postgresql.Driver";
  private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "postgres";
  private static final String PACKAGE = "spring/webchat/model/entity";

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
    localSessionFactoryBean.setDataSource(dataSource());
    localSessionFactoryBean.setPackagesToScan(PACKAGE);
    localSessionFactoryBean.setHibernateProperties(hibernateProperties());
    return localSessionFactoryBean;
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(DRIVER);
    dataSource.setUrl(JDBC_URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager hibernateTransactionManager() {
    HibernateTransactionManager transactionManager
        = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }

  private Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty(
        "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    return hibernateProperties;
  }

}
