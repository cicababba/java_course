package com.example.authentication.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthServiceProxy authServiceProxy;
    private final ObjectMapper mapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager, AuthServiceProxy authServiceProxy) {
        super(authenticationManager);
        this.authServiceProxy = authServiceProxy;
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(JwtProvider.header);
        if (Objects.nonNull(header) && header.startsWith(JwtProvider.prefix)) {
            String token = header.replace(JwtProvider.prefix + " ", "");
            DecodedJWT decodedJWT = JwtProvider.verifyJwt(token);
            ObjectNode userNode = this.mapper.readValue(decodedJWT.getClaim("user").asString(), ObjectNode.class);
            User user = this.mapper.convertValue(userNode, User.class);
            this.authServiceProxy.getByEmail(user.getEmail()).ifPresent(u -> SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u.getEmail(), null, null)));
        }
        chain.doFilter(req, res);
    }

}