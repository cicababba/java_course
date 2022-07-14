package q.bit.qcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import q.bit.qcommerce.model.Cart;
import q.bit.qcommerce.model.User;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUser(User user);

    List<Cart> findAllByUserAndCompleted(User user, boolean completed);

    @Query(nativeQuery = true, value = "SELECT * FROM cart WHERE user_id = ?1 AND completed = ?2")
    Cart strangeQuery(int userId, boolean completed);

    @Query("select c from Cart c where c.user = ?1 and c.completed = ?2")
    Cart anotherStrangeQuery(int userId, boolean completed);
}
