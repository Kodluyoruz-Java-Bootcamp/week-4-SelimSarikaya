package emlakcepte.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import emlakcepte.client.Payment;
import emlakcepte.client.PaymentServiceClient;
import emlakcepte.model.Product;
import emlakcepte.model.Realty;
import emlakcepte.model.User;
import emlakcepte.repository.ProductRepository;
import emlakcepte.request.ProductRequest;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserService userService;
	@Autowired
	PaymentServiceClient paymentServiceClient;

	public Product create(ProductRequest productRequest) {

		Payment paymentRequest = new Payment();
		paymentRequest.setAmount(productRequest.getAmount());
		ResponseEntity<Payment> paymentResponse = paymentServiceClient.create(paymentRequest);

		Optional<User> foundUser = userService.getById(productRequest.getUserId());
		List<Product> productList = productRepository.findAllByUserId(foundUser.get().getId());

		if (!foundUser.isPresent()) {
			System.out.println("user bulunamadı");
		}
		else {
			
			if (productRepository.getProductByUserId(productRequest.getUserId()) == null) {
				Product product = convert(productRequest);
				productRepository.save(product);
				product.setUser(foundUser.get());
				product.setExpireDate(LocalDate.now().plusMonths(productRequest.getAmount()));
				product.setAmount(productRequest.getAmount());
				productRepository.save(product);
				return product;
			}
			else {
				LocalDate expireDate = productRepository.getProductByUserId(productRequest.getUserId()).getExpireDate();
				expireDate = expireDate.plusMonths(productRequest.getAmount());
				productRepository.getProductByUserId(productRequest.getUserId()).setExpireDate(expireDate);
				Integer amount = productRepository.getProductByUserId(productRequest.getUserId()).getAmount();
				amount += productRequest.getAmount();
				productRepository.getProductByUserId(productRequest.getUserId()).setAmount(amount);
				productRepository.flush();
			}

	
		}
		
		return productRepository.getProductByUserId(productRequest.getUserId());

//		if (foundUser.get().getProduct() != null) {
//			LocalDate expireDate = foundUser.get().getProduct().getExpireDate();
//			expireDate = expireDate.plusMonths(productRequest.getAmount());
//			foundUser.get().getProduct().setExpireDate(expireDate);
//		} else {
//			Product product = convert(productRequest);
//			product.setUser(foundUser.get());
//			product.setExpireDate(LocalDate.now().plusMonths(productRequest.getAmount()));
//			Product savedProduct = productRepository.save(product);
//			foundUser.get().setProduct(product);
//
//			return savedProduct;
//
//		}
//
//		return foundUser.get().getProduct();

//		if (!user.isPresent()) {
//			System.out.println("user bulunamadı");
//		}
//		System.out.println(user.get().getProduct());
//		if (user.get().getProduct() != null) {
//			LocalDate expireDate = user.get().getProduct().getExpireDate();
//			expireDate = expireDate.plusMonths(product.getAmount());
//		    user.get().getProduct().setExpireDate(expireDate.plusMonths(product.getAmount()));
//		}
//		else {
//		Product savedProduct = productRepository.save(product);
//		savedProduct.setStartDate(LocalDate.now());
//		System.out.println(savedProduct.getStartDate());
//		savedProduct.setExpireDate(LocalDate.now().plusMonths(savedProduct.getAmount()));
//		System.out.println(savedProduct.getExpireDate());
//		savedProduct.setUser(product.getUser());
//		System.out.println(savedProduct.getUser());
//		user.get().setProduct(savedProduct);
//		System.out.println(user.get().getProduct());
//		return savedProduct;
//		}
//		return user.get().getProduct();

//		Product savedProduct = productRepository.save(product);
//		savedProduct.setStartDate(LocalDate.now());
//		savedProduct.setExpireDate(LocalDate.now().plusMonths(savedProduct.getAmount()));
//		savedProduct.setUserId(product.getUserId());
//		return savedProduct;
	}

	private Product convert(ProductRequest productRequest) {
		Product product = new Product();
		product.setUser(userService.getById(productRequest.getUserId()).get());
		product.setStartDate(LocalDate.now());
		product.setOk(true);
		return product;
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}

}
