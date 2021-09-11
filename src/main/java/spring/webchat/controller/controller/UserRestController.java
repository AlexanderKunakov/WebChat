package spring.webchat.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.webchat.model.entity.UserEntity;
import spring.webchat.model.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

  private final UserService userService;

  @PostMapping()
  public String addUser(@ModelAttribute("userEntity") UserEntity userEntity) {
    userService.add(userEntity);
    return userEntity.toString();
  }

  @GetMapping("/{id}")
  public UserEntity getUser(@PathVariable("id") int id) {
    return userService.getById(id);
  }

  @GetMapping("/username/{username}")
  public UserEntity getUser(@PathVariable("username") String username) {
    return userService.getByUsername(username);
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAuthority({'ADMIN'})")
  public String deleteUser(@PathVariable("id") int id) {
    userService.delete(id);
    return HttpStatus.OK.toString();
  }
}
