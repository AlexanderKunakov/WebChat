package spring.webchat.controller.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import spring.webchat.model.security.SecurityUser;

import java.util.Collection;

@Getter
@Setter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private SecurityUser user;
    private String token;

    public JwtAuthenticationToken(String token, SecurityUser user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.user = user;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
