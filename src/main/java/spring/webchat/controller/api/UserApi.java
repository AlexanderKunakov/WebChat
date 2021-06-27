package spring.webchat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.webchat.model.service.UserService;
import spring.webchat.model.entity.User;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {
  private UserService userService;

  @PostMapping("/add")
  @PreAuthorize("hasAuthority({'ADMIN'})")
  public String addUser(@ModelAttribute("user") User user) {
    userService.add(user);
    return user.toString();
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable("id") int id) {
    return userService.getById(id);
  }

  @GetMapping("/username/{username}")
  public User getUser(@PathVariable("username") String username) {
    return userService.getByUsername(username);
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAuthority({'ADMIN'})")
  public String deleteUser(@PathVariable("id") int id) {
    userService.delete(id);
    return HttpStatus.OK.toString();
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
