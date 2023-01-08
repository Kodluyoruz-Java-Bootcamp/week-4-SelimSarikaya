package emlakcepte.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import emlakcepte.model.Product;
import emlakcepte.request.ProductRequest;


@FeignClient(name = "emlakcepte-payment-service", url = "http://localhost:8085")
public interface PaymentServiceClient {
	
	@PostMapping(value = "/payments")
	ResponseEntity<Payment> create(@RequestBody Payment payment);


}
