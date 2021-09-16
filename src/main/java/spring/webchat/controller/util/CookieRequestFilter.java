package spring.webchat.controller.util;

import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CookieRequestFilter extends AbstractAuthenticationProcessingFilter {

    public CookieRequestFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
        setAuthenticationManager(super.getAuthenticationManager());
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String token = "";

        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies == null || cookies.length == 0) {
            throw new AuthenticationServiceException("Invalid token");
        }

        Cookie sessionCookie = Arrays.stream(cookies)
                .filter(cookie -> "JWT".equals(cookie.getName()))
                .findFirst()
                .orElse(null);

        if (sessionCookie == null || !StringUtils.hasLength(sessionCookie.getValue())) {
            throw new AuthenticationServiceException("Invalid token");
        }

        return new JwtAuthenticationToken(sessionCookie.getValue(), null, null);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
    }
}
