package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Stage;

@Repository
public interface StageRepository extends JpaRepository<Stage, Integer>{

	@Query("select o.stage from Orderer o where o.route.id=?1")
	Collection<Stage> findAllStagesByRoute(int routeId);
	
	@Query("select o.stage from Orderer o where o.route in (select r.route from Registration r where r.id=?1)")
	Collection<Stage> findAllStagesByRegistration(int registrationId);
	
	@Query("select s from Stage s where s.stageInstances.size=0 and s.orderers.size=0")
	Collection<Stage> findAllStagesThatCanBeDeleted();
	
	@Query("select l.stage from Lodge l order by l.rating asc")
	Collection<Stage> findAllStagesOrderedByAscendingAverageRating();
	
	@Query("select l.stage from Lodge l order by l.rating asc")
	Collection<Stage> findAllStagesByPilgrim();
	
	@Query("select o.stage from Orderer o where o.route in (select r.route from Registration r where r.pilgrim.userAccount.id=?1)")
	Collection<Stage> findAllStagesHistoryByPilgrim(int userAccountId);
	
	@Query("select b.lodge.stage from Booking b where b.pilgrim.userAccount.id=?1 and b.arrivalDate>current_date")
	Collection<Stage> findAllPendingStageByPilgrim(int userAccountId);
	
	
}
