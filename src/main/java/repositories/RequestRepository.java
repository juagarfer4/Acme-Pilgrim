package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;


@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
	
	@Query("select r from Request r where r.innkeeper.userAccount.id=?1 and r.status='ACCEPTED' or r.status='PENDING'")
	Collection<Request> conditionToPublishRequest(int userAccountId);
	
	@Query("select r from Request r where r.innkeeper.userAccount.id=?1")
	Collection<Request> findAllRequestsByInnkeeper(int userAccountId);
	
	@Query("select r from Request r where r.administrator.userAccount.id=?1 and r.status='PENDING'")
	Collection<Request> findAllRequestsByAdministratorToManage(int userAccountId);
	
	@Query("select r from Request r where r.administrator is null")
	Collection<Request> findAllRequestsWithoutAdministrator();
	
	@Query("select r from Request r where r.administrator.userAccount.id=?1 and r.status='PENDING'")
	Collection<Request> findAllRequestsPendingbyAdministrator(int userAccountId);
	
	
}
