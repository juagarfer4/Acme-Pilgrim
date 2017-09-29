package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)

public class Registration extends DomainEntity{
	
	// Constructors --------------------------------------------
	
	public Registration(){
		super();
	}
	
	// Attributes ----------------------------------------------
	
	private Date moment;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	// Relationships ---------------------------------------------
	
	private Pilgrim pilgrim;
	private Route route;
	private Collection<StageInstance> stageInstances;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Pilgrim getPilgrim() {
		return pilgrim;
	}

	public void setPilgrim(Pilgrim pilgrim) {
		this.pilgrim = pilgrim;
	}

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy="registration")
	public Collection<StageInstance> getStageInstances() {
		return stageInstances;
	}

	public void setStageInstances(Collection<StageInstance> stageInstances) {
		this.stageInstances = stageInstances;
	}

}
