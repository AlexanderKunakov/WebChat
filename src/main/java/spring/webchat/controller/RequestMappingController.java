package spring.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMappingController {

  @RequestMapping("/chat")
  public String chat(){
    return "chat";
  }

}
