package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Stage extends DomainEntity{
	
	// Constructors ----------------------------------------------------
	
	public Stage(){
		super();
	}
	
	// Attributes -------------------------------------------------------
	
	private String name;
	private String description;
	private GPS sourceLocation;
	private GPS destinationLocation;
	private double length;
	private double lengthMilles;
	private int difficultyLevel;
	//private Boolean isDeleted;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Valid
	@NotNull
	@AttributeOverrides({
			@AttributeOverride(name="title",
				column=@Column(name="sourceTitle")),
			@AttributeOverride(name="description",
				column=@Column(name="sourceDescription")),
			@AttributeOverride(name="longitude",
				column=@Column(name="sourceLongitude")),
			@AttributeOverride(name="latitude",
				column=@Column(name="sourceLatitude")),
			@AttributeOverride(name="altitude",
				column=@Column(name="sourceAltitude"))
	})
	public GPS getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(GPS sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Valid
	@NotNull
	@AttributeOverrides({
		@AttributeOverride(name="title",
			column=@Column(name="destinationTitle")),
		@AttributeOverride(name="description",
			column=@Column(name="destinationDescription")),
		@AttributeOverride(name="longitude",
			column=@Column(name="destinationLongitude")),
		@AttributeOverride(name="latitude",
			column=@Column(name="destinationLatitude")),
		@AttributeOverride(name="altitude",
			column=@Column(name="destinationAltitude"))
	})
	public GPS getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(GPS destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	
	@NotNull
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	
	@Transient
	public double getLengthMilles() {
		lengthMilles=length*0.621371192;
		return lengthMilles;
	}
	public void setLengthMilles(double lengthMilles) {
		this.lengthMilles = lengthMilles;
	}
	
	@NotNull
	@Range(min = 0 , max = 10)
	public int getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	// Relationships ----------------------------------------------------------
	
	private Collection<StageInstance> stageInstances;
	private Collection<Orderer> orderers;
	private Collection<Lodge> lodges;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="stage")
	public Collection<StageInstance> getStageInstances() {
		return stageInstances;
	}
	public void setStageInstances(Collection<StageInstance> stageInstances) {
		this.stageInstances = stageInstances;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="stage")
	public Collection<Orderer> getOrderers() {
		return orderers;
	}
	public void setOrderers(Collection<Orderer> orderers) {
		this.orderers = orderers;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="stage")
	public Collection<Lodge> getLodges() {
		return lodges;
	}
	public void setLodges(Collection<Lodge> lodges) {
		this.lodges = lodges;
	}

}
