package hiphadi.menu.domain.menu.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.menu.domain.Menu;
import hiphadi.menu.domain.menu.domain.Situation;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	@Query("SELECT m FROM Menu m JOIN FETCH m.product p JOIN FETCH p.category c " +
		"WHERE m.situation = :situation " +
		"ORDER BY c.priority ASC, CASE WHEN m.price IS NULL THEN 0 ELSE 1 END, " +
		"m.price DESC, p.customOrder ASC")
	List<Menu> findBySituationOrderByCategoryPriorityAndPrice(@Param("situation") Situation situation);
}
