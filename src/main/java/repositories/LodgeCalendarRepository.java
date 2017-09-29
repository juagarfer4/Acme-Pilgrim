package repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LodgeCalendar;

@Repository
public interface LodgeCalendarRepository extends JpaRepository<LodgeCalendar, Integer>{

	
	@Query("select lc.numberOfBeds from LodgeCalendar lc where lc.date=?1 and lc.lodge.id=?2")
	Integer numberOfFreeBeds(Date date,int lodgeId);
	
	@Query("select count(lc) from LodgeCalendar lc where lc.date=?1 and lc.lodge.id=?2")
	Integer existLodgeCalendar(Date date,int lodgeId);
	
	@Query("select lc from LodgeCalendar lc where lc.date=?1 and lc.lodge.id=?2")
	LodgeCalendar findLodgeCalendarByDateLodge(Date time, int lodgeId);
	
	@Query("select SUM(lc.numberOfBeds)from LodgeCalendar lc where lc.lodge.innkeeper.userAccount.id =?1 and MONTH(lc.date)=?2")
	Integer findBedsOfAnInnkeeperInAMonth(int innkeeperId, int nextMonth);
	
	@Query("select SUM(l.numberOfBeds)from Lodge l where l.innkeeper.userAccount.id =?1")
	Integer findBedsOfAnInnkeeper(int innkeeperId);
	
	
}
