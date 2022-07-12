package q.bit.qcommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    private String name;

    private String description;

    private float price;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;
}
