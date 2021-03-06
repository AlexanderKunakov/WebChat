package spring.webchat.controller.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatMappingController {

  @GetMapping("/")
  @PreAuthorize("hasAnyAuthority({'ADMIN', 'USER'})")
  public String generalChat() {
    return "index";
  }
}
