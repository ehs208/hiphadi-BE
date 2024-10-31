package hiphadi.menu.domain.product;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByOrderByCategoryDescIdDesc(Pageable pageable);

	@Query("""
    SELECT p FROM Product p
    WHERE 
        p.category < :categoryCursor 
        OR (p.category = :categoryCursor AND p.id < :idCursor)
    ORDER BY 
        p.category DESC, 
        p.id DESC
    """)
	List<Product> findByCursor(@Param("categoryCursor") String categoryCursor,
		@Param("idCursor") Long idCursor,
		Pageable pageable);
}
