import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String... args) {

        List<Role> roles = Collections.singletonList(new Role("1", "ADMIN"));
        User user = new User("username", "password", roles);
        UserDTO dto = fakeLogin(user);
        System.out.println(user);
        System.out.println(dto);
    }

    private static UserDTO fakeLogin(User user) {
        // questo metodo simula una chiamata api che restituisce uno userDTO
        // facciamo finta di estrarre un utente dal database (noi lo abbiamo come parametro del metodo)

        // TODO CHECK USERNAME E PASSWORD SUL DATABASE
        // TODO ABBIAMO OTTENUTO DAL DB UN UTENTE, ORA LO CONVERTIAMO IN UN UTENTE DTO
        UserCreationDTO dtoCreator = new UserCreationDTO();
        return dtoCreator.createUserDTO(user);
    }
}
