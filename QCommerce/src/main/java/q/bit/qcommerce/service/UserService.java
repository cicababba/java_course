package q.bit.qcommerce.service;

import org.apache.log4j.Logger;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import q.bit.qcommerce.dto.LiteUserDTO;
import q.bit.qcommerce.dto.UserDTO;
import q.bit.qcommerce.model.User;
import q.bit.qcommerce.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

   @Autowired
   private MapperService mapper;


    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        log.debug("searching for all users");

        return mapper.getMapper().map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO findByEmail(String email) throws Exception {
        User dbUser = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User with email " + email + " not found"));

        return mapper.getMapper().map(dbUser, UserDTO.class);
    }
    public LiteUserDTO findLiteByEmail(String email) throws Exception {
        User dbUser = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User with email " + email + " not found"));
        return mapper.getMapper().map(dbUser, LiteUserDTO.class);
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

    public void cleanInactiveUsers() {
        List<User> users = userRepository.findAll();
        log.info("found this users " + users);
        users = users.stream().filter(user -> Objects.nonNull(user.getLastAccess())
                && user.getLastAccess()
                .getTime() + 86400 < new Date()
                .getTime())
                .collect(Collectors.toList());
        log.info("users to delete" + users);
        userRepository.deleteAll(users);
    }
}
