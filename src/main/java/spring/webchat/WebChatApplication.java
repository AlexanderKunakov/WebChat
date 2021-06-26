package spring.webchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.webchat.model.dao.UserDaoImpl;
import spring.webchat.model.entity.User;

@SpringBootApplication
public class WebChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebChatApplication.class, args);
        System.out.println(new UserDaoImpl().get2(1));

    }

}
