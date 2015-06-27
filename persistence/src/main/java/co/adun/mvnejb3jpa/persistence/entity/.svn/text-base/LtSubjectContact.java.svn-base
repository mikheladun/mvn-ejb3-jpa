package co.adun.mvnejb3jpa.persistence.entity;

// Generated May 5, 2013 9:04:21 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



@XmlRootElement
@Entity
@Table(name = "LT_SUBJECT_CONTACT")
public class LtSubjectContact extends BaseEntity {

	private Long id;
	private LtUser ltUserByModifiedBy;
	private LtSubject ltSubject;
	private ContactTypeCode contactTypeCode;
	private PhoneTypeCode phoneTypeCode;
	private LtUser ltUserByCreateBy;
	private String subjectContactComment;
	private Date createDate;
	private Date modifiedDate;
	private Set<LtSubjectContactSource> ltSubjectContactSources = new HashSet<LtSubjectContactSource>(
			0);
	private Set<LtSubjectContactEmail> ltSubjectContactEmails = new HashSet<LtSubjectContactEmail>(
			0);
	private Set<LtSubjectContactPhone> ltSubjectContactPhones = new HashSet<LtSubjectContactPhone>(
			0);

	public LtSubjectContact() {
	}

	public LtSubjectContact(Long id, LtUser ltUserByModifiedBy,
			LtSubject ltSubject, ContactTypeCode contactTypeCode,
			LtUser ltUserByCreateBy, Date createDate, Date modifiedDate) {
		this.id = id;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.ltSubject = ltSubject;
		this.contactTypeCode = contactTypeCode;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

	public LtSubjectContact(Long id, LtUser ltUserByModifiedBy,
			LtSubject ltSubject, ContactTypeCode contactTypeCode,
			PhoneTypeCode phoneTypeCode, LtUser ltUserByCreateBy,
			String subjectContactComment, Date createDate, Date modifiedDate,
			Set<LtSubjectContactSource> ltSubjectContactSources,
			Set<LtSubjectContactEmail> ltSubjectContactEmails,
			Set<LtSubjectContactPhone> ltSubjectContactPhones) {
		this.id = id;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.ltSubject = ltSubject;
		this.contactTypeCode = contactTypeCode;
		this.phoneTypeCode = phoneTypeCode;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.subjectContactComment = subjectContactComment;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.ltSubjectContactSources = ltSubjectContactSources;
		this.ltSubjectContactEmails = ltSubjectContactEmails;
		this.ltSubjectContactPhones = ltSubjectContactPhones;
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
	@JoinColumn(name = "LT_SUBJECT_ID")
	public LtSubject getLtSubject() {
		return this.ltSubject;
	}

	@XmlTransient
	public void setLtSubject(LtSubject ltSubject) {
		this.ltSubject = ltSubject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTACT_TYPE_CODE_ID", nullable = false)
	public ContactTypeCode getContactTypeCode() {
		return this.contactTypeCode;
	}

	@XmlElement
	public void setContactTypeCode(ContactTypeCode contactTypeCode) {
		this.contactTypeCode = contactTypeCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHONE_TYPE_CODE_ID")
	public PhoneTypeCode getPhoneTypeCode() {
		return this.phoneTypeCode;
	}

	@XmlTransient
	public void setPhoneTypeCode(PhoneTypeCode phoneTypeCode) {
		this.phoneTypeCode = phoneTypeCode;
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

	@Column(name = "SUBJECT_CONTACT_COMMENT", length = 4000)
	public String getSubjectContactComment() {
		return this.subjectContactComment;
	}

	@XmlTransient
	public void setSubjectContactComment(String subjectContactComment) {
		this.subjectContactComment = subjectContactComment;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ltSubjectContact")
	public Set<LtSubjectContactSource> getLtSubjectContactSources() {
		return this.ltSubjectContactSources;
	}

	@XmlTransient
	public void setLtSubjectContactSources(
			Set<LtSubjectContactSource> ltSubjectContactSources) {
		this.ltSubjectContactSources = ltSubjectContactSources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ltSubjectContact")
	public Set<LtSubjectContactEmail> getLtSubjectContactEmails() {
		return this.ltSubjectContactEmails;
	}

	@XmlTransient
	public void setLtSubjectContactEmails(
			Set<LtSubjectContactEmail> ltSubjectContactEmails) {
		this.ltSubjectContactEmails = ltSubjectContactEmails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ltSubjectContact")
	public Set<LtSubjectContactPhone> getLtSubjectContactPhones() {
		return this.ltSubjectContactPhones;
	}

	@XmlTransient
	public void setLtSubjectContactPhones(
			Set<LtSubjectContactPhone> ltSubjectContactPhones) {
		this.ltSubjectContactPhones = ltSubjectContactPhones;
	}

}
