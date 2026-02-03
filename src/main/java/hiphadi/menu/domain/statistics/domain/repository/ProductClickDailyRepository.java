package hiphadi.menu.domain.statistics.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.statistics.domain.ProductClickDaily;

@Repository
public interface ProductClickDailyRepository extends JpaRepository<ProductClickDaily, Long> {

	@Modifying
	@Query(value = "INSERT INTO product_click_daily (product_id, click_date, click_count) " +
		"VALUES (:productId, :clickDate, 1) " +
		"ON DUPLICATE KEY UPDATE click_count = click_count + 1", nativeQuery = true)
	void upsertClickCount(@Param("productId") Long productId, @Param("clickDate") LocalDate clickDate);

	@Query("SELECT pcd FROM ProductClickDaily pcd WHERE pcd.clickDate BETWEEN :startDate AND :endDate " +
		"ORDER BY pcd.clickCount DESC")
	List<ProductClickDaily> findByClickDateBetweenOrderByClickCountDesc(
		@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
