package net.prescent.security;

import net.prescent.entity.CustomerEntity;
import net.prescent.entity.SellerEntity;
import net.prescent.entity.UserEntity;
import net.prescent.service.AccessTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Collection;

public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final AccessTokenService accessTokenService;

    public CustomAuthenticationFilter(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && accessTokenService.validateAccessToken(token)) {
            UserEntity user = accessTokenService.getUserFromToken(token);
            if (user != null) {
                Collection<? extends GrantedAuthority> authorities = getAuthorities(user);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user.getKey(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {

        if (user instanceof SellerEntity) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_SELLER"));
        }
        else if (user instanceof CustomerEntity) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }
        return Collections.emptyList();
    }
}