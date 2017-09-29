package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class LodgeAssessment extends DomainEntity{
	
	// Constructors ---------------------------------------------------
	
	public LodgeAssessment(){
		super();
	}
	
	// Attributes -----------------------------------------------------
	
	private Date creationMoment;
	private String comments;
	private Integer locationAppreciation;
	private Integer roomsAppreciation;
	private Integer serviceAppreciation;
	private Integer priceAppreciation;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Range(min = 0 , max = 10)
	public Integer getLocationAppreciation() {
		return locationAppreciation;
	}
	public void setLocationAppreciation(Integer locationAppreciation) {
		this.locationAppreciation = locationAppreciation;
	}
	
	@Range(min = 0 , max = 10)
	public Integer getRoomsAppreciation() {
		return roomsAppreciation;
	}
	public void setRoomsAppreciation(Integer roomsAppreciation) {
		this.roomsAppreciation = roomsAppreciation;
	}
	
	@Range(min = 0 , max = 10)
	public Integer getServiceAppreciation() {
		return serviceAppreciation;
	}
	public void setServiceAppreciation(Integer serviceAppreciation) {
		this.serviceAppreciation = serviceAppreciation;
	}
	
	@Range(min = 0 , max = 10)
	public Integer getPriceAppreciation() {
		return priceAppreciation;
	}
	public void setPriceAppreciation(Integer priceAppreciation) {
		this.priceAppreciation = priceAppreciation;
	}
	
	// Relationships -------------------------------------------------------
	
	private Booking booking;

	@Valid
	@OneToOne( optional = false )
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
