package q.bit.qcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import q.bit.qcommerce.model.Cart;
import q.bit.qcommerce.model.User;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUser(User user);

    List<Cart> findAllByUserAndCompleted(User user, boolean completed);
}
