package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints={@UniqueConstraint( columnNames = {"lodge_id" , "date" })})
public class LodgeCalendar extends DomainEntity{
	
	// Constructors -----------------------------------------
	
	public LodgeCalendar(){
		super();
	}
	
	// Attributes ----------------------------------------
	
	private Date date;
	private int numberOfBeds;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@NotNull
	@Min(0)
	public int getNumberOfBeds() {
		return numberOfBeds;
	}
	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	
	// Relationships ----------------------------------------
	
	private Lodge lodge;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Lodge getLodge() {
		return lodge;
	}
	public void setLodge(Lodge lodge) {
		this.lodge = lodge;
	}

}
