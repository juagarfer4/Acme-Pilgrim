
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Innkeeper extends Customer{
	
	// Constructors ----------------------------------------------
	
	public Innkeeper(){
		super();
	}
	
	// Attributes ------------------------------------------------
	
	// Relationships --------------------------------------------

	private Collection<Request> requests;
	private Collection<Lodge> lodges;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="innkeeper")
	public Collection<Request> getRequests() {
		return requests;
	}
	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="innkeeper")
	public Collection<Lodge> getLodges() {
		return lodges;
	}
	public void setLodges(Collection<Lodge> lodges) {
		this.lodges = lodges;
	}

}
