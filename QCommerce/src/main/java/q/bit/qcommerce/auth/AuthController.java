package q.bit.qcommerce.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import q.bit.qcommerce.dto.Response;
import q.bit.qcommerce.dto.UserDTO;
import q.bit.qcommerce.service.UserService;

import static q.bit.qcommerce.shared.Utils.buildResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody AuthDTO authDTO) {
        System.out.println("auth controller");
        return authService.login(authDTO.getEmail(), authDTO.getPassword());
    }

    @PostMapping("/signup")
    public Response createUser(@RequestBody UserDTO user) {
        try {
            if (user.getEmail().isBlank() || user.getPassword().isBlank())
                return buildResponse("Email and password are required", 400, null);

            if(userService.exists(user.getEmail()))
                return buildResponse("User with email " + user.getEmail() + " already exists", 400, null);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return buildResponse("Success", 200, null);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

    @GetMapping("/logout")
    public Response logout(@RequestHeader("Authorization") String token) {
        try {
            token = token.replace(JwtProvider.prefix + " ", "");
            long expire = JwtProvider.verifyJwt(token).getExpiresAt().getTime();
            InvalidatedToken invalidatedToken = new InvalidatedToken();
            invalidatedToken.setToken(token);
            invalidatedToken.setExpire(expire);
            invalidatedTokenRepository.save(invalidatedToken);
            return buildResponse("Logged out", 200, null);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }
}
