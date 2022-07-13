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
        User dbUser = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User with email " + email + " not found"));

        UserDTO userDto = new UserDTO();
        userDto.setName(dbUser.getName());
        userDto.setSurname(dbUser.getSurname());
        userDto.setEmail(dbUser.getEmail());
        userDto.setPassword(dbUser.getPassword());
        userDto.setBalance(dbUser.getBalance());
        return userDto;
    }

    public void save(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setBalance(userDto.getBalance());
        userRepository.save(user);
    }

    public void delete(String email) throws Exception {
        Optional<User> dbUser = userRepository.findByEmail(email);
        if(dbUser.isEmpty()) {
            throw new Exception("User with email " + email + " does not exist");
        }
        userRepository.delete(dbUser.get());
    }

    public boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }

    public void updateBalance(String email, double amount) throws Exception {
        User dbUser = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User with email " + email + " does not exist"));

        dbUser.setBalance(dbUser.getBalance() + amount);
        userRepository.save(dbUser);
    }
}
