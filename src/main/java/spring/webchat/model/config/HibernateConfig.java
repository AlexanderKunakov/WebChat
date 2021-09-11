package spring.webchat.model.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
  @Value("${spring.datasource.driver-class-name}")
  private String driver;

  @Value("${spring.datasource.url}")
  private String jdbcUrl;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${datasource.package.to.scan}")
  private String packageToScan;

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driver);
    dataSource.setUrl(jdbcUrl);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }

}
