package co.adun.mvnejb3jpa.persistence.entity;

// Generated May 5, 2013 9:04:21 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * SpecialProjectCode generated by hbm2java
 */
@XmlRootElement
@Entity
@Table(name = "SPECIAL_PROJECT_CODE", uniqueConstraints = @UniqueConstraint(columnNames = "ABBREVIATION"))
public class SpecialProjectCode extends BaseEntity {

	private Long id;
	private String abbreviation;
	private String description;
	private Date createDate;
	private Long createBy;
	private Date modifiedDate;
	private Long modifiedBy;
	private Set<LtLeadSpecialProject> ltLeadSpecialProjects = new HashSet<LtLeadSpecialProject>(
			0);

	public SpecialProjectCode() {
	}

	public SpecialProjectCode(Long id, String abbreviation, String description,
			Date createDate, Long createBy, Date modifiedDate, Long modifiedBy) {
		this.id = id;
		this.abbreviation = abbreviation;
		this.description = description;
		this.createDate = createDate;
		this.createBy = createBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}

	public SpecialProjectCode(Long id, String abbreviation, String description,
			Date createDate, Long createBy, Date modifiedDate, Long modifiedBy,
			Set<LtLeadSpecialProject> ltLeadSpecialProjects) {
		this.id = id;
		this.abbreviation = abbreviation;
		this.description = description;
		this.createDate = createDate;
		this.createBy = createBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.ltLeadSpecialProjects = ltLeadSpecialProjects;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	@XmlElement
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ABBREVIATION", unique = true, nullable = false, length = 100)
	public String getAbbreviation() {
		return this.abbreviation;
	}

	@XmlElement
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	
	@Column(name = "CREATE_DATE", nullable = false)
	public Date getCreateDate() {
		return this.createDate;
	}

	@XmlElement
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_BY", nullable = false, precision = 22, scale = 0)
	public Long getCreateBy() {
		return this.createBy;
	}

	@XmlElement
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	
	@Column(name = "MODIFIED_DATE", nullable = false)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	@XmlElement
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "MODIFIED_BY", nullable = false, precision = 22, scale = 0)
	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	@XmlElement
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialProjectCode")
	public Set<LtLeadSpecialProject> getLtLeadSpecialProjects() {
		return this.ltLeadSpecialProjects;
	}

	@XmlTransient
	public void setLtLeadSpecialProjects(
			Set<LtLeadSpecialProject> ltLeadSpecialProjects) {
		this.ltLeadSpecialProjects = ltLeadSpecialProjects;
	}

}
