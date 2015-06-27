package co.adun.mvnejb3jpa.persistence.entity;

// Generated May 5, 2013 9:04:21 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



@XmlRootElement
@Entity
@Table(name = "LT_SUBJECT_PHOTO")
public class LtSubjectPhoto extends BaseEntity {

	private Long id;
	private LtUser ltUserByModifiedBy;
	private LtSubject ltSubject;
	private LtUser ltUserByCreateBy;
	private String image;
	private String description;
	private String source;
	private Date createDate;
	private Date modifiedDate;

	public LtSubjectPhoto() {
	}

	public LtSubjectPhoto(Long id, LtUser ltUserByModifiedBy,
			LtSubject ltSubject, LtUser ltUserByCreateBy, String image,
			String source, Date createDate, Date modifiedDate) {
		this.id = id;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.ltSubject = ltSubject;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.image = image;
		this.source = source;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

	public LtSubjectPhoto(Long id, LtUser ltUserByModifiedBy,
			LtSubject ltSubject, LtUser ltUserByCreateBy, String image,
			String description, String source, Date createDate,
			Date modifiedDate) {
		this.id = id;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.ltSubject = ltSubject;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.image = image;
		this.description = description;
		this.source = source;
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

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({
//			@JoinColumn(name = "LT_SUBJECT_ID", referencedColumnName = "ID", nullable = false),
//			@JoinColumn(name = "LT_SUBJECT_LSID", referencedColumnName = "LSID", nullable = false) })
	@JoinColumn(name = "LT_SUBJECT_ID", nullable = false)
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

	@Column(name = "IMAGE", nullable = false, length = 100)
	public String getImage() {
		return this.image;
	}

	@XmlElement
	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SOURCE", nullable = false, length = 100)
	public String getSource() {
		return this.source;
	}

	@XmlElement
	public void setSource(String source) {
		this.source = source;
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
