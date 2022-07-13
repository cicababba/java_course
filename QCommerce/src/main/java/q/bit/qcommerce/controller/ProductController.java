package q.bit.qcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import q.bit.qcommerce.dto.ProductDTO;
import q.bit.qcommerce.dto.Response;
import q.bit.qcommerce.model.Product;
import q.bit.qcommerce.repository.ProductRepository;
import q.bit.qcommerce.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static q.bit.qcommerce.shared.Utils.buildResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public Response getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        products.forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTOs.add(productDTO);
        });
        return buildResponse("Success", 200, productDTOs);
    }

    @GetMapping("/{page}/{size}")
    public Response getAllProductsPaginated(@PathVariable int page, @PathVariable int size) {
        try {
            Page<Product> products = productService.findAllPaginated(page, size);

            return buildResponse("Success", 200, products);
        } catch (Exception e) {
            return buildResponse(e.getMessage(), 500, null);
        }
    }

    @GetMapping("/{id}")
    public Response getProductById(@PathVariable long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        Product product = optProduct.get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return buildResponse("Success", 200, productDTO);
    }

    @PostMapping
    public Response createProduct(@RequestBody ProductDTO productDTO) {

        if(productRepository.findByNameAndDescription(productDTO.getName(), productDTO.getDescription()).isPresent())
            return buildResponse("Product already exists", 400, null);

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product = productRepository.save(product);
        return buildResponse("Success", 200, product);
    }

    @PutMapping("/{id}")
    public Response updateProduct(@PathVariable long id, @RequestBody ProductDTO productDTO) {
        Optional<Product> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty()) {
            return buildResponse("Product not found", 400, null);
        }
        Product product = optProduct.get();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
        return buildResponse("Success", 200, product);
    }

    @DeleteMapping("/{id}")
    public Response deleteProduct(@PathVariable long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty()) {
            return buildResponse("Product not found", 400, null);
        }
        productRepository.delete(optProduct.get());
        return buildResponse("Success", 200, null);
    }
}
