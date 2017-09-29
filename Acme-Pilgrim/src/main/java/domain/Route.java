package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

@Entity
@Access(AccessType.PROPERTY)
public class Route extends DomainEntity{
	
	// Constructors -------------------------------------------------
	
	public Route(){
		super();
	}
	
	// Attributes ---------------------------------------------------
	
	private String name;
	private String description;
	private Double lengthRating;
	private Double difficultyRating;
	private Boolean isDeleted;
	
	@NotNull
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Transient
	public Double getLengthRating() {
		return lengthRating;
	}
	
	public void setLengthRating(Double lengthRating) {
		this.lengthRating = lengthRating;
	}

	@Transient
	public Double getDifficultyRating() {
		return difficultyRating;
	}
	public void setDifficultyRating(Double difficultyRating) {
		this.difficultyRating = difficultyRating;
	}
	@NotNull
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	// Relationships --------------------------------------------------------
	
	private Collection<Registration> registrations;
	private Collection<Orderer> orderers;

	@Valid
	@NotNull
	@OneToMany(mappedBy="route")
	public Collection<Registration> getRegistrations() {
		return registrations;
	}
	public void setRegistrations(Collection<Registration> registrations) {
		this.registrations = registrations;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="route")
	public Collection<Orderer> getOrderers() {
		return orderers;
	}
	public void setOrderers(Collection<Orderer> orderers) {
		this.orderers = orderers;
	}
	

}
