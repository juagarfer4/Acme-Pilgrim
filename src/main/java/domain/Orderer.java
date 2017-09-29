package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Orderer extends DomainEntity{
	
	// Constructors --------------------------------------------
	
	public Orderer(){
		super();
	}
	
	// Attributes ----------------------------------------------
	
	private Integer identifier;

	@NotNull
	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	
	// Relationships ---------------------------------------------
	
	private Route route;
	private Stage stage;

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
	@ManyToOne(optional=false)
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
