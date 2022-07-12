package q.bit.qcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import q.bit.qcommerce.dto.UserDTO;
import q.bit.qcommerce.model.User;
import q.bit.qcommerce.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        users.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setSurname(user.getSurname());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTOs.add(userDTO);
        });
        return userDTOs;
    }

    public UserDTO findByEmail(String email) throws Exception {
        Optional<User> dbUser = userRepository.findByEmail(email);
        if (dbUser.isEmpty()) {
            throw new Exception("User with email " + email + " does not exist");
        }
        UserDTO userDto = new UserDTO();
        userDto.setName(dbUser.get().getName());
        userDto.setSurname(dbUser.get().getSurname());
        userDto.setEmail(dbUser.get().getEmail());
        userDto.setPassword(dbUser.get().getPassword());
        return userDto;
    }

    public void save(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    public void delete(String email) throws Exception {
        Optional<User> dbUser = userRepository.findByEmail(email);
        if(dbUser.isEmpty()) {
            throw new Exception("User with email " + email + " does not exist");
        }
        userRepository.delete(dbUser.get());
    }
}
