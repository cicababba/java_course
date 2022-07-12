package q.bit.qcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import q.bit.qcommerce.dto.Response;
import q.bit.qcommerce.dto.UserDTO;
import q.bit.qcommerce.model.User;
import q.bit.qcommerce.repository.UserRepository;
import q.bit.qcommerce.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static q.bit.qcommerce.shared.Utils.buildResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Response getAllUsers() {
        try {
            List<UserDTO> userDTOs = userService.findAll();

            if (userDTOs.isEmpty()) {
                return buildResponse("No users found", 404, null);
            }

            return buildResponse("Success", 200, userDTOs);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

    @GetMapping("/{email}")
    public Response getByEmail(@PathVariable String email) {
       try {
           UserDTO userDto = userService.findByEmail(email);
           return buildResponse("Success", 200, userDto);
       } catch (Exception e) {
           return buildResponse(e.getMessage(), 500, null);
       }
    }

    @PostMapping
    public Response createUser(@RequestBody UserDTO user) {
        try {
            if (user.getEmail().isBlank() || user.getPassword().isBlank()) {
                return buildResponse("Email and password are required", 400, null);
            }
            userService.findByEmail(user.getEmail());
            userService.save(user);
            return buildResponse("Success", 200, null);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

    @PutMapping
    public Response updateUser(@RequestBody UserDTO user) {
        try {
            userService.findByEmail(user.getEmail());
            userService.save(user);
            return buildResponse("Success", 200, null);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

    @DeleteMapping("/{email}")
    public Response deleteUser(@PathVariable String email) {
        try {
            userService.findByEmail(email);
            userService.delete(email);
            return buildResponse("Success", 200, null);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

}
