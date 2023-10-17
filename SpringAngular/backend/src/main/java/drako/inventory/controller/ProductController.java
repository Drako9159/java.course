package drako.inventory.controller;

import drako.inventory.exception.ResourceNotFoundException;
import drako.inventory.model.Product;
import drako.inventory.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventary-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> products = this.productService.listProducts();
        logger.info("Products get:");
        products.forEach((product) -> logger.info(product.toString()));
        return products;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        logger.info("Product to add: " + product);
        return this.productService.addProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = this.productService.findProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            throw new ResourceNotFoundException("Not found product: " + id);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product productDb = this.productService.findProductById(id);
        if (productDb == null) throw new ResourceNotFoundException("Product not found: " + id);
        productDb.setDescription(product.getDescription());
        productDb.setPrice(product.getPrice());
        productDb.setStock(product.getStock());
        this.productService.addProduct(productDb);
        return ResponseEntity.ok(productDb);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int id) {
        Product product = this.productService.findProductById(id);
        if (product == null) throw new ResourceNotFoundException("Product not found: " + id);
        this.productService.deleteProductById(product.getIdProduct());
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
