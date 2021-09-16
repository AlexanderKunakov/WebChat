package spring.webchat.controller.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import spring.webchat.controller.util.AuthenticationSuccessHandler;
import spring.webchat.controller.util.CookieRequestFilter;
import spring.webchat.controller.util.JwtRequestFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final JwtRequestFilter jwtRequestFilter;
  private final AuthenticationSuccessHandler successHandler;
  private SimpleUrlAuthenticationFailureHandler failureHandler = getFailureHandler();

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            .authorizeRequests()
            .antMatchers("/register").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/resources/**").permitAll()
            .antMatchers("/*.js").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/api/v1/auth").permitAll()
            .anyRequest().authenticated()
            .and()

            .formLogin()
//            .failureHandler(failureHandler)
            .failureUrl("/login")
            .loginPage("/login")
            .successHandler(successHandler)
            .successForwardUrl("/")
            .and()

            .cors().disable()
            .csrf().disable();

    httpSecurity.addFilterAfter(getCookieRequestFilter
            (new AntPathRequestMatcher("/account")), UsernamePasswordAuthenticationFilter.class);
  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  public SimpleUrlAuthenticationFailureHandler getFailureHandler() {
    SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/login");
    handler.setDefaultFailureUrl("/login");
    handler.setUseForward(true);

    return handler;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  public CookieRequestFilter getCookieRequestFilter(RequestMatcher requestMatcher) {

    CookieRequestFilter cookieRequestFilter = new CookieRequestFilter(requestMatcher);
    cookieRequestFilter.setAuthenticationFailureHandler(failureHandler);

    return cookieRequestFilter;
  }
}
