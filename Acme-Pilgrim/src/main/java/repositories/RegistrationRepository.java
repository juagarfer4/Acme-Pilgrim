package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Registration;
import domain.Route;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer>{
	
	@Query("select r from Registration r where r.pilgrim.userAccount.id=?1")
	Collection<Registration> findAllRegistrationsByPilgrim(int userAccountId);

	@Query("select r.route from Registration r where r.pilgrim.userAccount.id=?1")
	Collection<Route> findAllRoutesByPilgrim(int userAccountId);
	
	
}
