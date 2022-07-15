package q.bit.qcommerce.auth;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "invalidated_token")
@Data
public class InvalidatedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "LONGTEXT")
    private String token;

    private long expire;
}
