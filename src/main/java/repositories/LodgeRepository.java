package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lodge;

@Repository
public interface LodgeRepository extends JpaRepository<Lodge, Integer>{
	
	@Query("select i.lodges from Innkeeper i where i in (select r.innkeeper from Request r where r.status='ACCEPTED')")
	Collection<Lodge> findAllLodgesWhoseRequestIsAcepted();

	@Query("select l from Lodge l where l.innkeeper.userAccount.id=?1")
	Collection<Lodge> findAllLodgesByInnkeeper(int innkeeperId);
	
	@Query("select b.lodge from Booking b where b.lodge.innkeeper.userAccount.id=?1 group by b.lodge order by b.lodge.bookings.size desc")
	Collection<Lodge> findAllLodgesSortedByDescendingNumberOfBookingsByInnkeeper(int innkeeperId);
	
	@Query("select avg(b.lodgeAssessment.locationAppreciation) from Booking b where b.lodge.id=?1")
	Double calculateLocationRating(int routeId);
	
	@Query("select avg(b.lodgeAssessment.roomsAppreciation) from Booking b where b.lodge.id=?1")
	Double calculateRoomsRating(int routeId);
	
	@Query("select avg(b.lodgeAssessment.serviceAppreciation) from Booking b where b.lodge.id=?1")
	Double calculateServiceRating(int routeId);
	
	@Query("select avg(b.lodgeAssessment.priceAppreciation) from Booking b where b.lodge.id=?1")
	Double calculatePriceRating(int routeId);
	
	@Query("select l from Lodge l where l.innkeeper.userAccount.id=?1 order by l.rating asc")
	Collection<Lodge> findAllLodgesOrderedByIncreaseAverageRatingByInnkeeper(int innkeeperId);
	
	@Query("select l from Lodge l where l.innkeeper.userAccount.id=?1 group by l.stage order by l.price desc")
	Collection<Lodge> findAllLodgesOrderedByDescendingPriceByInnkeeper(int innkeeperId);
	
	@Query("select l from Lodge l order by l.bookings.size desc")
	Collection<Lodge> findAllLodgesOrderedByDescendingNumberOfBooking();
	
	@Query("select b.lodge from Booking b where b.pilgrim.userAccount.id=?1")
	Collection<Lodge> findAllLodgesByPilgrim(int userAccountId);
	
	@Query("select l from Lodge l where l.innkeeper.userAccount.id=?1 and l.bookings.size=0")
	Collection<Lodge> findAllLodgesToDelete(int userAccountId);
}
