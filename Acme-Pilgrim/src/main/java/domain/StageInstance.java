package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class StageInstance extends DomainEntity{
	
	// Constructors ------------------------------------------------------
	
	public StageInstance(){
		super();
	}
	
	// Attributes -------------------------------------------------------
	
	private Date startingMoment;
	private Date endingMoment;
	private String comments;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	@NotNull
	public Date getStartingMoment() {
		return startingMoment;
	}
	public void setStartingMoment(Date startingMoment) {
		this.startingMoment = startingMoment;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	@NotNull
	public Date getEndingMoment() {
		return endingMoment;
	}
	public void setEndingMoment(Date endingMoment) {
		this.endingMoment = endingMoment;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	// Relationships ------------------------------------------------
	
	private Registration registration;
	private Stage stage;
	private Assessment assessment;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
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
	
	@OneToOne(cascade = CascadeType.ALL, optional=true)
	@Valid
	public Assessment getAssessment(){
		return assessment;
	}
	public void setAssessment(Assessment assessment){
		this.assessment=assessment;
	}

}
