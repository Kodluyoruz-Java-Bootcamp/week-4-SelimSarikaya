package emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emlakcepte.model.Product;
import emlakcepte.model.User;
import emlakcepte.request.ProductRequest;
import emlakcepte.service.ProductService;
import emlakcepte.service.UserService;


@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping
	public Product create(@RequestBody ProductRequest productRequest) {
		Product product = productService.create(productRequest);
		return product;
	}
	@GetMapping
	public List<Product> getAll() {
		return productService.getAll();
	}
	
	
	

}
