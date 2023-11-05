package com.pwel.allegrotrader.product.domain;

import com.pwel.allegrotrader.product.domain.exception.ProductNotFoundException;
import com.pwel.allegrotrader.product.infrastructure.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts(String name) {
        if (name == null) {
            return productRepository.findAll();
        }
        return productRepository.findProductsByName(name);
    }

    public void addNewProduct(Product product) {
//        Optional<Product> productOptional = productRepository
//                .findProductByName(product.getName());
//
//        if (productOptional.isPresent()) {
//            throw new IllegalStateException("Product name already taken.");
//        }
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long productId, String name, String category) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + productId + " does not exist."));

        if (name != null && name.length() > 0) {
            Optional<Product> productOptional = productRepository
                    .findProductByName(name);
            if (!Objects.equals(product.getName(), name)) {
                if (productOptional.isPresent()) {
                    throw new IllegalStateException("Product name already taken.");
                }
                product.setName(name);
                log.info("Product: {} has been updated with new name: {}", product.getName(), name);
            }
        }

        if (category != null && category.length() > 0) {
            product.setCategory(category);
            log.info("Product: {} has been updated with new category: {}", product.getName(), category);
        }
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product with id: " + productId + " does not exist.");
        }
        productRepository.deleteById(productId);
        log.info("Product with id: {} has been deleted", productId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product with id: " + productId + " does not exist."));
    }
}
