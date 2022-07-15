package q.bit.qcommerce.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import q.bit.qcommerce.model.User;
import q.bit.qcommerce.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;
    private final InvalidatedTokenRepository invalidatedTokenRepository;

    private final ObjectMapper mapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, InvalidatedTokenRepository invalidatedTokenRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(JwtProvider.header);
        System.out.println("Auth filter " + header);
        if(Objects.nonNull(header) && header.startsWith(JwtProvider.prefix)) {
            String token = header.replace(JwtProvider.prefix + " ", "");
            DecodedJWT decodedJWT = JwtProvider.verifyJwt(token);
            ObjectNode userNode = this.mapper.readValue(decodedJWT.getClaim("user").asString(), ObjectNode.class);
            User user = this.mapper.convertValue(userNode, User.class);
            if(this.invalidatedTokenRepository.findByToken(token).isEmpty())
                this.userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u.getEmail(),null, null));
                });
        }
        chain.doFilter(req, res);
    }

}
