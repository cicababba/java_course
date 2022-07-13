package q.bit.qcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("/{email}")
    public Response getAllByEmail(@PathVariable String email) {
        try {
            List<Cart> orders = cartService.findAllByUser(email);
            if (orders.isEmpty()) {
                return buildResponse("No orders found for this user", 200, null);
            }
            return buildResponse("Success", 200, orders);
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

    @PutMapping("/{cart_id}/pay/{email}")
    public Response pay(@PathVariable(name = "cart_id") Long cartId, @PathVariable String email) {
        try {
            cartService.pay(cartId, email);
            return buildResponse("Payment completed", 200, null);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

    @GetMapping("/{email}/{completed}")
    public Response getAllByEmailAndCompleted(@PathVariable String email, @PathVariable boolean completed) {
        try {
            List<Cart> orders = cartService.findAllByUserAndCompleted(email, completed);
            if (orders.isEmpty()) {
                return buildResponse("No " + (completed ? " completed " : " open ") + " orders found for this user", 200, null);
            }
            return buildResponse("Success", 200, orders);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

    @PostMapping("/{email}/fast_order")
    @Transactional(rollbackFor = Exception.class)
    public Response createAndPay(@RequestBody List<ProductDTO> products, @PathVariable String email) throws Exception {
        Cart cart = cartService.createCart(email, products);
//        Thread.sleep(5000);//todo simula un portale esterno
//            if(2==2) //todo scommentare per testare il transactional
//                throw new Exception("Esplosione");
        cartService.pay(cart.getId(), email);
        return buildResponse("Success", 200, cart);
    }
}
