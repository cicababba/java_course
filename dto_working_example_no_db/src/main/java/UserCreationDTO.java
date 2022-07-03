import java.util.List;
import java.util.stream.Collectors;

public class UserCreationDTO {


    public UserDTO createUserDTO(User user) {
        List<String> stringRoles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        return new UserDTO(user.getName(), stringRoles);
    }
}