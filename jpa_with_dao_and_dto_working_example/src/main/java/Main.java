import dao.Dao;
import dao.JpaUserDao;
import dto.UserDTO;
import entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    static JpaUserDao jpaUserDao = new JpaUserDao();

    public static void main(String[] args) throws Exception {


//        User user1 = jpaUserDao.get(1).orElseGet(() -> new User("no user found", "no user found"));//ottieni user con id 1
        User user3 = jpaUserDao.get(3).orElseGet(() -> new User("no user found", "no user found"));//ottieni user con id 1
        UserDTO userDTO3 = fakeGetUserAPI(3);
        System.out.println(userDTO3);
//        TODO crud di esempio
//        jpaUserDao.update(user1, new String[]{"Jake", "jake@domain.com"});
//        jpaUserDao.save(new User("Giovanni", "giovanni@domain.com"));
//        jpaUserDao.save(new User("Pippo", "pippo@domain.com"));
        jpaUserDao.delete(user3);
//        jpaUserDao.getAll().forEach(user -> System.out.println("Eccomi!!! -> " + user.getName()));



        //TODO ESEMPIO REALE
        UserDTO userDTO = fakeGetUserAPI(1);
        System.out.println(userDTO);

        List<UserDTO> usersDTO = fakeGetAllUsersAPI();
        System.out.println(usersDTO);
    }


  public static UserDTO fakeGetUserAPI(int id) throws Exception {
        User user = jpaUserDao.get(id).orElseThrow(() -> new Exception("No user found"));
        UserDTO userDTO = userToUserDTO(user);
        return userDTO;

  }

    public static List<UserDTO> fakeGetAllUsersAPI() throws Exception {
        List<User> users = jpaUserDao.getAll();
        List<UserDTO> usersDTO = users.stream().map(user -> userToUserDTO(user)).collect(Collectors.toList());
        return usersDTO;

    }

    private static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}