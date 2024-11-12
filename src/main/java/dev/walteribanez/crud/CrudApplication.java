package dev.walteribanez.crud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@SpringBootApplication
@RestController
public class CrudApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrudApplication.class, args);
	}


    //Add a default response to not return on aws health check port 80 as false or error

	@GetMapping("/")
	public String defaultResponse() {
		return "Hello from Spring Boot!";
	}



}
