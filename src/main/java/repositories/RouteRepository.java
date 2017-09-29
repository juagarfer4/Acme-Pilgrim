package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>{
	
	@Query("select r.route from Registration r where r.pilgrim.userAccount.id=?1 and r.route.isDeleted=false")
	Collection<Route> findAllNotDeletedRoutesByPilgrim(int userAccountId);
	
	@Query("select r from Route r where r.isDeleted=false order by r.registrations.size desc")
	Collection<Route> findAllNotDeletedRoutesInDescendingOrderOfRegistrations();
	
	@Query("select r from Route r order by r.difficultyRating asc")
	Collection<Route> findAllRoutesDeleted();
	
	@Query("select r from Route r where r.isDeleted=false")
	Collection<Route> findAllRoutesThatAreNotCancelled();
	
	@Query("select r.route from Registration r where r.route not in (select re.route from Registration re where re.pilgrim.userAccount.id=?1) and r.route.isDeleted=false")
	Collection<Route> findAllRoutesToRegister(int userAccountId);
	
	@Query("select r from Route r where r.registrations.size=0 and r.orderers.size=0 and r.isDeleted=false")
	Collection<Route> findAllRoutesToDelete();
	
	@Query("select avg(si.assessment.lengthRating) from StageInstance si where si.stage in (select o.stage from Orderer o where o.route.id = ?1)")
	Double calculateLengthRating(int routeId);
	
	@Query("select avg(si.assessment.difficultyRating) from StageInstance si where si.stage in (select o.stage from Orderer o where o.route.id = ?1)")
	Double calculateDifficultyRating(int routeId);

	
}
