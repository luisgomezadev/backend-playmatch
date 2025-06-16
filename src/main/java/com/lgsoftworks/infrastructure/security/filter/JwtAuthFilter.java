package com.lgsoftworks.infrastructure.security.filter;

import com.lgsoftworks.infrastructure.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public JwtAuthFilter(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Ignorar rutas públicas
        String path = request.getServletPath();
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            jwt = authHeader.substring(7);

            userEmail = jwtService.extractUsername(jwt);

            if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) {
                log.debug("El JWT no contiene un nombre de usuario.");
                filterChain.doFilter(request, response);
                return;
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            boolean isTokenValid = jwtService.isTokenValid(jwt, userDetails);
            boolean isTokenExpired = jwtService.isTokenExpired(jwt);
            boolean canBeRenewed = jwtService.canTokenBeRenewed(jwt);

            if (!isTokenValid || (isTokenExpired && !canBeRenewed)) {
                log.debug("El JWT no es válido");
                SecurityContextHolder.clearContext();
                filterChain.doFilter(request, response);
                return;
            }

            if (isTokenExpired) {
                log.debug("El JWT ha expirado y se va a renovar");
                String newToken = jwtService.renewToken(jwt, userDetails);
                response.setHeader("Authorization", "Bearer " + newToken);
            }

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception e) {
            log.error("Error al procesar el JWT: {}", e.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

        filterChain.doFilter(request, response);

    }
}
