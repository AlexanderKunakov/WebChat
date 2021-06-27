package spring.webchat.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.webchat.model.entity.User;

@Controller
public class RegisterController {

  @RequestMapping("/register")
  public String register(Model model) {
    User user = new User();

    model.addAttribute("user", user);
    return "register";
  }

}
