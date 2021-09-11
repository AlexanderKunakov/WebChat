package spring.webchat.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring.webchat.controller.util.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  JwtRequestFilter jwtRequestFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().disable()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/*.js").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/api/v1/auth").permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Autowired
  public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
    this.jwtRequestFilter = jwtRequestFilter;
  }
}
