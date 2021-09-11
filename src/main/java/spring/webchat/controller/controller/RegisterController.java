package spring.webchat.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.webchat.model.entity.UserEntity;

@Controller
public class RegisterController {

  @RequestMapping("/register")
  public String register(@ModelAttribute("userEntity") UserEntity userEntity) {
    return "register";
  }
}
