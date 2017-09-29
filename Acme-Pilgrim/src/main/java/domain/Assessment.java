package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Assessment extends DomainEntity{
	
	// Constructors -----------------------------------------------------
	
	public Assessment(){
		super();
	}
	
	// Attributes --------------------------------------------------------
	
	private Date creationMoment;
	private int lengthRating;
	private int difficultyRating;
	private String comment;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@Range(min = 0 , max = 10)
	public int getLengthRating() {
		return lengthRating;
	}
	public void setLengthRating(int lengthRating) {
		this.lengthRating = lengthRating;
	}
	
	@Range(min = 0 , max = 10)
	public int getDifficultyRating() {
		return difficultyRating;
	}
	public void setDifficultyRating(int difficultyRating) {
		this.difficultyRating = difficultyRating;
	}
	
	public String getComment(){
		return comment;
	}
	public void setComment(String comment){
		this.comment=comment;
	}
	
	// Relationships -----------------------------------------------
	
	private StageInstance stageInstance;

	@Valid
	@OneToOne(optional=false)
	@NotNull
	public StageInstance getStageInstance() {
		return stageInstance;
	}
	public void setStageInstance(StageInstance stageInstance) {
		this.stageInstance = stageInstance;
	}

}
