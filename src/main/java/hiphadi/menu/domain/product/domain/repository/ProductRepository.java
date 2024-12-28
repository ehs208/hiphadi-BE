package hiphadi.menu.domain.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// @Query("SELECT p FROM Product p ORDER BY p.category.priority ASC, CASE WHEN p.price IS NULL THEN 0 ELSE 1 END, p.price DESC, p.customOrder ASC")
	// List<Product> findAllByCustomOrder();
}

