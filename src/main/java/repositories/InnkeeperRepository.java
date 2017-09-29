package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Innkeeper;

@Repository
public interface InnkeeperRepository extends JpaRepository<Innkeeper, Integer>{
	
	@Query("select i from Innkeeper i where i.userAccount.id=?1")
	Innkeeper findByUserAccountId(int userAccountId);
	
	@Query("select count(b) from Booking b where b.lodge.innkeeper.userAccount.id=?1 and month(b.arrivalDate)=?2 and year(b.arrivalDate)=?3 ")
	Double findNumberOfBookingInMyLodgesNextMonth(int userAccountId, int nextMonth , int year);
	
	@Query("select count(l) from Lodge l where l.innkeeper.userAccount.id=?1")
	Double findNumberOfMyLodges(int userAccountId);
	
	@Query("select i from Innkeeper i order by i.lodges.size desc")
	Collection<Innkeeper> findAllInnkeeperOrderedDescendingNumberOfLodges();
}


