package spring.webchat.controller.controller.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import spring.webchat.model.pojo.Message;

@Controller
public class MessageController {

  @MessageMapping("/message")
  @SendTo("/chat/messages")
  public Message getMessages(Message message) {
    System.out.println("From " + message.getFrom() + ": " + message.getMessage());
    return message;
  }

}
