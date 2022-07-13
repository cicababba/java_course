package q.bit.qcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import q.bit.qcommerce.model.Product;
import q.bit.qcommerce.repository.ProductRepository;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAllPaginated(int pageNumber, int size) {
        Pageable page = PageRequest.of(pageNumber, size);
        return productRepository.findAll(page);
    }
}
