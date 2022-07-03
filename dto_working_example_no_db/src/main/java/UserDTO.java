import java.util.List;

public class UserDTO {
    private String name;
    private List<String> roles;

    public UserDTO(String name, List<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}