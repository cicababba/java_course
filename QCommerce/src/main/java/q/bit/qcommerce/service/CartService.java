package q.bit.qcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${env:test}")
    private String env;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }


    public Cart createCart(String email, List<ProductDTO> products) throws Exception {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new Exception("User with email " + email + " does not exist"));


        List<Product> productList = productRepository.findAllById(products.stream().map(ProductDTO::getId).collect(Collectors.toList()));
        double total = productList.stream().mapToDouble(Product::getPrice).sum();

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(productList);
        cart.setTotal(total);

        return cartRepository.save(cart);
    }


    public List<Cart> findAllByUser(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User with email " + email + " does not exist"));
        return cartRepository.findAllByUser(user);
    }

    public void pay(long cartId, String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User with email " + email + " does not exist"));

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new Exception("Cart with id " + cartId + " does not exist"));

        if("prod".equals(env) && !cart.getUser().getEmail().equals(user.getEmail()))
            throw new Exception("User with email " + email + " does not own this cart");

        if (cart.getTotal() > user.getBalance())
            throw new Exception("User with email " + email + " does not have enough money");

        user.setBalance(user.getBalance() - cart.getTotal());
        userRepository.save(user);
        cart.setCompleted(true);
        cartRepository.save(cart);
    }

    public List<Cart> findAllByUserAndCompleted(String email, boolean completed) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User with email " + email + " does not exist"));
        cartRepository.findAllByUserAndCompleted(user, completed);
        return cartRepository.findAllByUserAndCompleted(user, completed);
    }

}
