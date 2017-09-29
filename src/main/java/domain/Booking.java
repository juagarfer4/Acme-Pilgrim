package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table( indexes = {@Index (columnList = "lodge_id")})
public class Booking extends DomainEntity{
	
	// Constructors ---------------------------------------------------
	
	public Booking(){
		super();
	}
	
	// Attributes ------------------------------------------------
	
	private Date creationMoment;
	private Date arrivalDate;
	private int numberOfNights;
	private int numberOfBedsRequested;
	private Double price;
	private Boolean isCancelled;
	private String comments;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	@Past
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@NotNull
	@Min(1)
	public int getNumberOfNights() {
		return numberOfNights;
	}
	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}
	
	@NotNull
	@Min(1)
	public int getNumberOfBedsRequested() {
		return numberOfBedsRequested;
	}
	public void setNumberOfBedsRequested(int numberOfBedsRequested) {
		this.numberOfBedsRequested = numberOfBedsRequested;
	}
	
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	@Transient
	public Double getPrice() {
		//return this.getLodge().getPrice()*this.getNumberOfBedsRequested()*this.getNumberOfNights();
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@NotNull
	public Boolean getIsCancelled() {
		return isCancelled;
	}
	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	// Relationships -----------------------------------------------------

	private Pilgrim pilgrim;
	private LodgeAssessment lodgeAssessment;
	private Lodge lodge;

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
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	public LodgeAssessment getLodgeAssessment() {
		return lodgeAssessment;
	}
	public void setLodgeAssessment(LodgeAssessment lodgeAssessment) {
		this.lodgeAssessment = lodgeAssessment;
	}
	
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
