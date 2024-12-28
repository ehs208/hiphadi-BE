package hiphadi.menu.domain.menuAvailability.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.menuAvailability.domain.MenuAvailability;
import hiphadi.menu.domain.menuAvailability.domain.Situation;

@Repository
public interface MenuAvailabilityRepository extends JpaRepository<MenuAvailability, Long> {
	@Query("SELECT m FROM MenuAvailability m JOIN m.product p JOIN p.category c WHERE m.situation = :situation ORDER BY c.priority ASC, CASE WHEN m.price IS NULL THEN 0 ELSE 1 END, m.price DESC, p.customOrder ASC")
	List<MenuAvailability> findBySituationOrderByCategoryPriorityAndPrice(@Param("situation") Situation situation);

}
