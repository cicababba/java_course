package q.bit.qcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import q.bit.qcommerce.dto.ProductDTO;
import q.bit.qcommerce.dto.Response;
import q.bit.qcommerce.model.Cart;
import q.bit.qcommerce.service.CartService;

import java.util.List;

import static q.bit.qcommerce.shared.Utils.buildResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public Response getAllCarts() {
        try {
            List<Cart> carts = cartService.findAll();

            if (carts.isEmpty()) {
                return buildResponse("No orders found", 404, null);
            }

            return buildResponse("Success", 200, carts);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

    @PostMapping("/{email}")
    public Response createCart(@RequestBody List<ProductDTO> products,@PathVariable String email) {
        try {

            Cart cart = cartService.createCart(email, products);
            return buildResponse("Success", 200, cart);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }
}
