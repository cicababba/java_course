package q.bit.qcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import q.bit.qcommerce.dto.ProductDTO;
import q.bit.qcommerce.model.Cart;
import q.bit.qcommerce.model.Product;
import q.bit.qcommerce.model.User;
import q.bit.qcommerce.repository.CartRepository;
import q.bit.qcommerce.repository.ProductRepository;
import q.bit.qcommerce.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart createCart(String email, List<ProductDTO> products) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new Exception("User with email " + email + " does not exist");
        }

        List<Product> productList = productRepository.findAll();
        double total = productList.stream().mapToDouble(Product::getPrice).sum();

        Cart cart = new Cart();
        cart.setUser(user.get());
        cart.setProducts(productList);
        cart.setTotal(total);

        return cartRepository.save(cart);
    }
}
