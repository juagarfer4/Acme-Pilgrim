package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Orderer;

@Repository
public interface OrdererRepository extends JpaRepository<Orderer, Integer>{

	
	
	@Query("select count(o) from Orderer o where o.route.id=?1")
	Integer numbrerOrdererByRouteId(int id);



}
