package q.bit.qcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import q.bit.qcommerce.dto.Response;
import q.bit.qcommerce.dto.UserDTO;
import q.bit.qcommerce.model.User;
import q.bit.qcommerce.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static q.bit.qcommerce.shared.Utils.buildResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Response getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            return buildResponse("No users found", 404, null);
        }

        List<UserDTO> userDTOs = new ArrayList<>();
        users.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setSurname(user.getSurname());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTOs.add(userDTO);
        });
        return buildResponse("Success", 200, userDTOs);
    }

    @GetMapping("/{email}")
    public Response getByEmail(@PathVariable String email) {
        Optional<User> dbUser = userRepository.findByEmail(email);
        if (dbUser.isEmpty()) {
            throw new IllegalArgumentException("User with email " + email + " does not exist");
        }
        UserDTO userDto = new UserDTO();
        userDto.setName(dbUser.get().getName());
        userDto.setSurname(dbUser.get().getSurname());
        userDto.setEmail(dbUser.get().getEmail());
        userDto.setPassword(dbUser.get().getPassword());
        return buildResponse("Success", 200, userDto);
    }

    @PostMapping
    public Response createUser(@RequestBody UserDTO user) {
        if (user.getEmail().isBlank() || user.getPassword().isBlank()) {
            return buildResponse("Email and password are required", 400, null);
        }
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser.isPresent()) {
            return buildResponse("User with email " + user.getEmail() + " already exists", 400, null);
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser = userRepository.save(newUser);

        return buildResponse("Success", 200, newUser);
    }

    @PutMapping
    public Response updateUser(@RequestBody UserDTO user) {
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser.isEmpty()) {
            return buildResponse("User with email " + user.getEmail() + " does not exist", 400, null);
        }
        User optUser = dbUser.get();
        optUser.setEmail(user.getEmail());
        optUser.setPassword(user.getPassword());
        optUser.setName(user.getName());
        optUser.setSurname(user.getSurname());
        userRepository.save(optUser);
        return buildResponse("Success", 200, optUser);
    }

    @DeleteMapping("/{email}")
    public Response deleteUser(@PathVariable String email) {
        Optional<User> dbUser = userRepository.findByEmail(email);
        if (dbUser.isEmpty()) {
            return buildResponse("User with email " + email + " does not exist", 400, null);
        }
        userRepository.delete(dbUser.get());
        return buildResponse("Success", 200, null);
    }

}
