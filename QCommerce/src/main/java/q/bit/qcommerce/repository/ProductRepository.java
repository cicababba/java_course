package q.bit.qcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import q.bit.qcommerce.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByNameAndDescription(String name, String description);

}
