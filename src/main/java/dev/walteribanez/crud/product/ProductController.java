package dev.walteribanez.crud.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {


    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getProducts() { 
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registerNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }


    //Make the put mapping and get from queryparams de id
    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }


    @DeleteMapping
    public ResponseEntity<Object> deleteProduct(@RequestBody Product product) {
        return productService.deleteProduct(product.getId());
    }
    
}
