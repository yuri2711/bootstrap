package ru.yuri.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String>l = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if(l.contains("ROLE_ADMIN"))response.sendRedirect("/admin");
        else if(l.contains("ROLE_USER")) response.sendRedirect("/people");
    }
}
