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



/**
 * LtSubjectContactPhone generated by hbm2java
 */
@Entity
@Table(name = "LT_SUBJECT_CONTACT_PHONE")
public class LtSubjectContactPhone extends BaseEntity {

	private CompositeId id;
	private LtUser ltUserByModifiedBy;
	private PhoneTypeCode phoneTypeCode;
	private LtSubjectContact ltSubjectContact;
	private LtUser ltUserByCreateBy;
	private Long phoneNumber;
	private Date phoneValidDate;
	private Date createDate;
	private Date modifiedDate;

	public LtSubjectContactPhone() {
	}

	public LtSubjectContactPhone(CompositeId id,
			LtUser ltUserByModifiedBy, PhoneTypeCode phoneTypeCode,
			LtSubjectContact ltSubjectContact, LtUser ltUserByCreateBy,
			Long phoneNumber, Date phoneValidDate, Date createDate,
			Date modifiedDate) {
		this.id = id;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.phoneTypeCode = phoneTypeCode;
		this.ltSubjectContact = ltSubjectContact;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.phoneNumber = phoneNumber;
		this.phoneValidDate = phoneValidDate;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "LT_SUBJECT_CONTACT_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "compId", column = @Column(name = "PHONE_TYPE_CODE_ID", nullable = false, precision = 22, scale = 0)) })
	public CompositeId getId() {
		return this.id;
	}

	public void setId(CompositeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFIED_BY", nullable = false)
	public LtUser getLtUserByModifiedBy() {
		return this.ltUserByModifiedBy;
	}

	public void setLtUserByModifiedBy(LtUser ltUserByModifiedBy) {
		this.ltUserByModifiedBy = ltUserByModifiedBy;
	}

	@MapsId(value="compId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHONE_TYPE_CODE_ID", nullable = false, insertable = false, updatable = false)
	public PhoneTypeCode getPhoneTypeCode() {
		return this.phoneTypeCode;
	}

	public void setPhoneTypeCode(PhoneTypeCode phoneTypeCode) {
		this.phoneTypeCode = phoneTypeCode;
	}

	@MapsId(value="id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LT_SUBJECT_CONTACT_ID", nullable = false, insertable = false, updatable = false)
	public LtSubjectContact getLtSubjectContact() {
		return this.ltSubjectContact;
	}

	public void setLtSubjectContact(LtSubjectContact ltSubjectContact) {
		this.ltSubjectContact = ltSubjectContact;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATE_BY", nullable = false)
	public LtUser getLtUserByCreateBy() {
		return this.ltUserByCreateBy;
	}

	public void setLtUserByCreateBy(LtUser ltUserByCreateBy) {
		this.ltUserByCreateBy = ltUserByCreateBy;
	}

	@Column(name = "PHONE_NUMBER", nullable = false, precision = 22, scale = 0)
	public Long getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	@Column(name = "PHONE_VALID_DATE", nullable = false, length = 7)
	public Date getPhoneValidDate() {
		return this.phoneValidDate;
	}

	public void setPhoneValidDate(Date phoneValidDate) {
		this.phoneValidDate = phoneValidDate;
	}

	
	@Column(name = "CREATE_DATE", nullable = false)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	@Column(name = "MODIFIED_DATE", nullable = false)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
