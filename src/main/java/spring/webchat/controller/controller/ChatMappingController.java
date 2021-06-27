package spring.webchat.controller.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatMappingController {

  @RequestMapping("/")
  @PreAuthorize("hasAnyAuthority({'ADMIN', 'USER'})")
  public String generalChat() {
    return "index";
  }

  @RequestMapping("/chat")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String chat() {
    return "chat";
  }

}
