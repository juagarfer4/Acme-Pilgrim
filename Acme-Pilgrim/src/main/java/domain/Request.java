package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table( indexes = {@Index (columnList = "administrator_id")})
public class Request extends DomainEntity{
	
	// Constructors ---------------------------------------------------
	
	public Request(){
		super();
	}
	
	// Attributes -----------------------------------------------------
	
	private String code;
	private String title;
	private Date creationMoment;
	private String description;
	private String status;
	private String comments;
	
	@NotBlank
	@Column(unique = true)
	//@Pattern(regexp = "^\\w$")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
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
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
//	@Pattern.List({
//	    @Pattern(regexp = "PENDING"),
//	    @Pattern(regexp = "ACCEPTED"),
//	    @Pattern(regexp = "REJECTED")
//	})
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	// Relationships -----------------------------------------------------
	
	private Innkeeper innkeeper;
	private Administrator administrator;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Innkeeper getInnkeeper() {
		return innkeeper;
	}
	public void setInnkeeper(Innkeeper innkeeper) {
		this.innkeeper = innkeeper;
	}
	
	@Valid
	@ManyToOne(optional=false)
	@NotNull
	public Administrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

}
