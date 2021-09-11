package spring.webchat.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spring.webchat.controller.util.JwtUtil;
import spring.webchat.model.pojo.AuthRequest;
import spring.webchat.model.pojo.AuthResponse;

@RequiredArgsConstructor
@Controller
public class AuthMappingController {
  private final AuthenticationManager authenticationManager;

  private final UserDetailsService userDetailsService;

  private final JwtUtil jwtUtil;

  @GetMapping(value = "/login")
  public String login() {
    return "login";
  }

  @PostMapping("api/v1/auth")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
    authenticateUserOrThrowException(authRequest);

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthResponse(jwt));
  }

  private void authenticateUserOrThrowException(AuthRequest authRequest) {
    try {
      final String username = authRequest.getUsername();
      final String password = authRequest.getPassword();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Invalid credentials", e);
    }
  }
}
