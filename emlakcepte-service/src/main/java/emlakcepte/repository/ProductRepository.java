package emlakcepte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emlakcepte.model.Product;
import emlakcepte.model.User;
import emlakcepte.request.ProductRequest;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findAllByUserId(Integer id);
	Product getProductByUserId(Integer id);

}
