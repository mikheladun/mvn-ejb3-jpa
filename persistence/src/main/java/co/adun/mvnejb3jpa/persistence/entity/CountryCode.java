package co.adun.mvnejb3jpa.persistence.entity;

// Generated May 5, 2013 9:04:21 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = "COUNTRY_CODE", uniqueConstraints = @UniqueConstraint(columnNames = "ABBREVIATION"))
public class CountryCode extends BaseEntity {

    private Long id;
    private LtUser ltUserByModifiedBy;
    private LtUser ltUserByCreateBy;
    private String abbreviation;
    private String description;
    private Date createDate;
    private Date modifiedDate;

    public CountryCode() {
    }

    public CountryCode(Long id, LtUser ltUserByModifiedBy, LtUser ltUserByCreateBy, String abbreviation, String description, Date createDate,
	    Date modifiedDate) {
	this.id = id;
	this.ltUserByModifiedBy = ltUserByModifiedBy;
	this.ltUserByCreateBy = ltUserByCreateBy;
	this.abbreviation = abbreviation;
	this.description = description;
	this.createDate = createDate;
	this.modifiedDate = modifiedDate;
    }

    public CountryCode(Long id, LtUser ltUserByModifiedBy, LtUser ltUserByCreateBy, String abbreviation, String description, Date createDate,
	    Date modifiedDate, Set<LtSubject> ltSubjects, Set<LtSubjectCitizenshipCountry> ltSubjectCitizenshipCountries,
	    Set<LtIdentifyingNumber> ltIdentifyingNumbers) {
	this.id = id;
	this.ltUserByModifiedBy = ltUserByModifiedBy;
	this.ltUserByCreateBy = ltUserByCreateBy;
	this.abbreviation = abbreviation;
	this.description = description;
	this.createDate = createDate;
	this.modifiedDate = modifiedDate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODIFIED_BY", nullable = false)
    public LtUser getLtUserByModifiedBy() {
	return this.ltUserByModifiedBy;
    }

    @XmlTransient
    public void setLtUserByModifiedBy(LtUser ltUserByModifiedBy) {
	this.ltUserByModifiedBy = ltUserByModifiedBy;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_BY", nullable = false)
    public LtUser getLtUserByCreateBy() {
	return this.ltUserByCreateBy;
    }

    @XmlTransient
    public void setLtUserByCreateBy(LtUser ltUserByCreateBy) {
	this.ltUserByCreateBy = ltUserByCreateBy;
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

    @Column(name = "MODIFIED_DATE", nullable = false)
    public Date getModifiedDate() {
	return this.modifiedDate;
    }

    @XmlElement
    public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
    }

}
