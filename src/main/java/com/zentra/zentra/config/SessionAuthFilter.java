package com.zentra.zentra.config;

import com.zentra.zentra.domain.Auth.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SessionAuthFilter extends OncePerRequestFilter {

    private AuthService authService;
    public SessionAuthFilter(AuthService authService) {
        this.authService = authService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userSessionToken = readCookie(request, "session");
        if (userSessionToken != null) {
            try {
                var user = authService.sessionAuth(userSessionToken);
                var auth = new UsernamePasswordAuthenticationToken(
                        user, null, List.of()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch (Exception e) {
                // ignored
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Unauthorized\"}");
                System.out.println("Unauthorized");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
    private String readCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        for (var c : request.getCookies()) {
            if (name.equals(c.getName())) return c.getValue();
        }
        return null;
    }

}
