package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Pilgrim extends Customer{
	
	// Constructors ------------------------------------------------------------
	
	public Pilgrim(){
		super();
	}
	
	// Attributes -------------------------------------------------------------
	
	private Date birthDate;
	private String nationality;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	@Past
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@NotBlank
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	// Relationships ----------------------------------------------------------
	
	private Collection<Registration> registrations;
	private Collection<Booking> bookings;

	@Valid
	@NotNull
	@OneToMany(mappedBy="pilgrim")
	public Collection<Registration> getRegistrations() {
		return registrations;
	}
	public void setRegistrations(Collection<Registration> registrations) {
		this.registrations = registrations;
	}
	
	
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="pilgrim")
	public Collection<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}
	
}
