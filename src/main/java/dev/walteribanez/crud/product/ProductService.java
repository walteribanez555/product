package dev.walteribanez.crud.product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public List<Product> getProducts() { 
        return productRepository.findAll();
    }

    public ResponseEntity<Object> addNewProduct(Product product) { 
       Optional<Product> resp = productRepository.findProductByName(product.getName());
        HashMap<String, Object> datos = new HashMap<>();



       if(resp.isPresent() ){
            datos.put("error" , true);
            datos.put("Message"," Ya existe un producto con ese nombre");
           return new ResponseEntity<>(
            datos,
            HttpStatus.BAD_REQUEST
           );
       }

        productRepository.save(product);

        datos.put("data", product);
        datos.put("message", "Se guardo con exito");


        return new ResponseEntity<>(
            datos,
            HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateProduct(Product product) {
        Optional<Product> resp = productRepository.findById(product.getId());


        HashMap<String, Object> datos = new HashMap<>();

        if(resp.isPresent() && product.getId() == null){
            datos.put("error" , true);
            datos.put("Message"," No existe un producto con ese id");
           return new ResponseEntity<>(
            datos,
            HttpStatus.CONFLICT
           );
        }

        productRepository.save(product);

        datos.put("data", product);
        datos.put("message", "Se actualizo con exito");

        return new ResponseEntity<>(
            datos,
            HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteProduct(Long id ) { 
        HashMap<String, Object> datos = new HashMap<>();


        boolean exists = productRepository.existsById(id);

        if(!exists){
            datos.put("error" , true);
            datos.put("Message"," No existe un producto con ese id");
           return new ResponseEntity<>(
            datos,
            HttpStatus.CONFLICT
           );
        }

        productRepository.deleteById(id);

        datos.put("message", "Se elimino con exito");

        return new ResponseEntity<>(
            datos,
            HttpStatus.CREATED
        );

        
    }

}
