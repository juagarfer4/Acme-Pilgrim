package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Transient;

@Entity
@Access(AccessType.PROPERTY)
@Table( indexes = {@Index (columnList = "innkeeper_id")})
public class Lodge extends DomainEntity{
	
	// Constructors -----------------------------------------------
	
	public Lodge(){
		super();
	}
	
	// Attributes -------------------------------------------------
	
	private String name;
	private String address;
	private GPS coordinates;
	private String webSite;
	private String contactPhone;
	private String lodgeDescription;
	private int numberOfBeds;
	private double price;
	private Double locationRating;
	private Double roomsRating;
	private Double serviceRating;
	private Double priceRating;
	private Double rating;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Valid
	@NotNull
	public GPS getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(GPS coordinates) {
		this.coordinates = coordinates;
	}
	
	@URL
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
	@NotBlank
	@Pattern(regexp = "^\\d+$")
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@NotBlank
	public String getLodgeDescription() {
		return lodgeDescription;
	}
	public void setLodgeDescription(String lodgeDescription) {
		this.lodgeDescription = lodgeDescription;
	}
	
	@NotNull
	@Min(1)
	public int getNumberOfBeds() {
		return numberOfBeds;
	}
	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	@NotNull
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Transient
	public Double getLocationRating() {
		return locationRating;
	}
	public void setLocationRating(Double locationRating) {
		this.locationRating = locationRating;
	}
	
	@Transient
	public Double getRoomsRating() {
		return roomsRating;
	}
	public void setRoomsRating(Double roomsRating) {
		this.roomsRating = roomsRating;
	}
	
	@Transient
	public Double getServiceRating() {
		return serviceRating;
	}
	public void setServiceRating(Double serviceRating) {
		this.serviceRating = serviceRating;
	}
	
	@Transient
	public Double getPriceRating() {
		return priceRating;
	}
	public void setPriceRating(Double priceRating) {
		this.priceRating = priceRating;
	}
	
	@Transient
	public Double getRating() {
		if(this.getLocationRating()!=null && this.getRoomsRating()!=null && this.getServiceRating()!=null && this.getPriceRating()!=null){
			rating=(this.getLocationRating()+this.getRoomsRating()+this.getServiceRating()+this.getPriceRating())/(double)4;
		}else{
			rating=null;
		}
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	// Relationships ---------------------------------------------------
	
	private Innkeeper innkeeper;
	private Collection<Booking> bookings;
	private Stage stage;
	private Collection<LodgeCalendar> lodgeCalendars;
	
	@Valid
	@ManyToOne( optional = false )
	public Innkeeper getInnkeeper() {
		return innkeeper;
	}
	public void setInnkeeper(Innkeeper innkeeper) {
		this.innkeeper = innkeeper;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="lodge")
	public Collection<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}
	
	@Valid
	@NotNull
	@ManyToOne( optional = false )
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="lodge")
	public Collection<LodgeCalendar> getLodgeCalendars() {
		return lodgeCalendars;
	}
	public void setLodgeCalendars(Collection<LodgeCalendar> lodgeCalendars) {
		this.lodgeCalendars = lodgeCalendars;
	}

}
