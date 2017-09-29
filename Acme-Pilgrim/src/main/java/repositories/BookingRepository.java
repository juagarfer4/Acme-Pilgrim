package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	@Query("select b from Booking b where b.pilgrim.userAccount.id=?1")
	Collection<Booking> findAllBookingsByPilgrim(int userAccountId);
	
	@Query("select b from Booking b where b.pilgrim.userAccount.id=?1 and b.isCancelled=false")
	Collection<Booking> findAllNotCancelledBookingsByPilgrim(int userAccountId);
	
	@Query("select b from Booking b where b.pilgrim.userAccount.id=?1 and b.arrivalDate<current_date and b.lodgeAssessment is null")
	Collection<Booking> findAllBookingsByPilgrimWithoutAssessment(int userAccountId);
	
	@Query("select b from Booking b where b.lodge.innkeeper.userAccount.id=?1 and DATE(?2) BETWEEN DATE(b.arrivalDate) and DATE(b.arrivalDate)+b.numberOfNights")
	Collection<Booking> findAllBookingByInnkeeperAndDate(int userAccountId, Date date);
	
}
