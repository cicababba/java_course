package q.bit.qcommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts;//orders
}
