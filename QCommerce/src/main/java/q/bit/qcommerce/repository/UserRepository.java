package q.bit.qcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import q.bit.qcommerce.model.User;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);
    boolean existsByEmail(String email);
}
