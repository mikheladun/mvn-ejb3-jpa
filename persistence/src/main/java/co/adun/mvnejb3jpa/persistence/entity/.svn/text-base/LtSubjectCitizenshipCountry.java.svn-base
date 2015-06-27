package co.adun.mvnejb3jpa.persistence.entity;

// Generated May 5, 2013 9:04:21 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



@XmlRootElement
@Entity
@Table(name = "LT_SUBJECT_CITIZENSHIP_COUNTRY")
public class LtSubjectCitizenshipCountry extends BaseEntity {

	private CompositeId id;
	private LtUser ltUserByModifiedBy;
	private LtSubject ltSubject;
	private LtUser ltUserByCreateBy;
	private CountryCode countryCode;
	private Date createDate;
	private Date modifiedDate;

	public LtSubjectCitizenshipCountry() {
	}

	public LtSubjectCitizenshipCountry(CompositeId id,
			LtUser ltUserByModifiedBy, LtSubject ltSubject,
			LtUser ltUserByCreateBy, CountryCode countryCode, Date createDate,
			Date modifiedDate) {
		this.id = id;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.ltSubject = ltSubject;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.countryCode = countryCode;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "LT_SUBJECT_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "compId", column = @Column(name = "COUNTRY_CODE_ID", nullable = false, precision = 22, scale = 0))})
	public CompositeId getId() {
		return this.id;
	}

	@XmlElement
	public void setId(CompositeId id) {
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

	@MapsId(value="id")
	@JoinColumn(name = "LT_SUBJECT_ID", nullable = false, insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	public LtSubject getLtSubject() {
		return this.ltSubject;
	}

	@XmlTransient
	public void setLtSubject(LtSubject ltSubject) {
		this.ltSubject = ltSubject;
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

	@MapsId(value="compId")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUNTRY_CODE_ID", nullable = false, insertable = false, updatable = false)
	public CountryCode getCountryCode() {
		return this.countryCode;
	}

	@XmlElement
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
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
