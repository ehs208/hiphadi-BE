package hiphadi.menu.domain.product.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p JOIN FETCH p.category c LEFT JOIN FETCH p.image " +
		"ORDER BY c.priority ASC, p.customOrder ASC")
	List<Product> findAllOrderedByCategoryAndCustomOrder();

	boolean existsByImageId(Long imageId);
}
