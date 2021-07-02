package spring.webchat.controller.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.webchat.model.entity.User;

@Controller
public class RegisterController {

  @RequestMapping("/register")
  public String register(@ModelAttribute("user") User user) {
    return "register";
  }

}
