package com.amadeus.config.security;

import com.amadeus.exception.AuthAppException;
import com.amadeus.exception.ErrorType;
import com.amadeus.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeaderParameters = request.getHeader("Authorization");
        if (authHeaderParameters != null && authHeaderParameters.startsWith("Bearer ")
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            String token = authHeaderParameters.substring(7);
            Optional<Long> authid = jwtTokenManager.getByIdFromToken(token);

            if (authid.isPresent()){
                System.out.println("Token is valid...." + authid.get());
                UserDetails userDetails = jwtUserDetails.getUserById(authid.get());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                throw new AuthAppException(ErrorType.JWT_TOKEN_CREATE_ERROR);
            }
        }
        filterChain.doFilter(request, response);
    }

}
