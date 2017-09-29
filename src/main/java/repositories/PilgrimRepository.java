package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Pilgrim;

@Repository
public interface PilgrimRepository extends JpaRepository<Pilgrim, Integer>{
	
	@Query("select p from Pilgrim p where p.userAccount.id=?1")
	Pilgrim findByUserAccountId(int userAccountId);
	
	@Query("select p from Pilgrim p order by p.registrations.size desc")
	Collection<Pilgrim> findAllPilgrimsInDescendingOrderOfRegistrations();

	@Query("select p from Pilgrim p where p.name like concat('%', ?1, '%') or p.surname like concat('%', ?1, '%') or p.email like concat('%', ?1, '%')")
	Collection<Pilgrim> findAllPilgrimsByKeyword(String keyword);
	
	@Query("select b.pilgrim from Booking b where b.lodge in (select l from Lodge l where l.innkeeper.userAccount.id = ?1) group by b.pilgrim.nationality")
	Collection<Pilgrim> findAllPilgrimsBookedInLodgeOfInnkeeper(int innkeeperId);
	
	@Query("select b.pilgrim from Booking b where b.lodge.innkeeper.userAccount.id=?1 and b.pilgrim=(select p from Pilgrim p where p.bookings.size=(select max(pi.bookings.size) from Pilgrim pi)) group by b.pilgrim.nationality")
	Collection<Pilgrim> findAllPilgrimsMoreBookingsInLodgeOfInnkeeper(int innkeeperId);
	
	@Query("select p from Pilgrim p  where p in (select b.pilgrim from Booking b where b.lodge.innkeeper.userAccount.id= ?1 ) order by p.birthDate desc")
	Collection<Pilgrim> findAllPilgrimsBookedInLodgeOfInnkeeperOrderedByDescendigBirthDate(int innkeeperId);
	
	@Query("select p from Pilgrim p order by p.birthDate desc")
	Collection<Pilgrim> findAllPilgrimsOrderedByDescendigBirthDate();
	
	
}