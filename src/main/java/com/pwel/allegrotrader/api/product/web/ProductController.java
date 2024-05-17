package com.pwel.allegrotrader.api.product.web;


import com.pwel.allegrotrader.api.product.domain.Product;
import com.pwel.allegrotrader.api.product.domain.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<Product> getProductsByName(
            @RequestParam(required = false) String name
    ) {
        return productService.getProducts(name);
    }

    @GetMapping("/{productId}")
    public Product getProduct(
            @PathVariable("productId") Long productId
    ) {
        return productService.getProductById(productId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
        return ResponseEntity.ok("Product " + product.getName() + " added successfully");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        productService.updateProduct(productId, name, category);
        return ResponseEntity.ok("Product Id: " + productId + " updated successfully");
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

}
