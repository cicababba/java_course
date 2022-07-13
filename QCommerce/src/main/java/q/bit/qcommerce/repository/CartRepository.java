package q.bit.qcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import q.bit.qcommerce.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
