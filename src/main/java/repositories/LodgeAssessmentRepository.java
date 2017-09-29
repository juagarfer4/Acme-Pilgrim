package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LodgeAssessment;


@Repository
public interface LodgeAssessmentRepository extends JpaRepository<LodgeAssessment, Integer>{

	
	@Query("select la from LodgeAssessment la where la.booking.pilgrim.userAccount.id=?1")
	Collection<LodgeAssessment> findAllLodgeAssessmentByPilgrim(int userAccountId);

}
