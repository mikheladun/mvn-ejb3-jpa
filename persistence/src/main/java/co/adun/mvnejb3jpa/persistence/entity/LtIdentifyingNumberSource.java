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
 * LtIdentifyingNumberSource generated by hbm2java
 */
@Entity
@Table(name = "LT_IDENTIFYING_NUMBER_SOURCE")
public class LtIdentifyingNumberSource extends BaseEntity {

	private CompositeId id;
	private SourceCode sourceCode;
	private LtUser ltUserByModifiedBy;
	private LtIdentifyingNumber ltIdentifyingNumber;
	private LtUser ltUserByCreateBy;
	private Date createDate;
	private Date modifiedDate;

	public LtIdentifyingNumberSource() {
	}

	public LtIdentifyingNumberSource(CompositeId id,
			SourceCode sourceCode, LtUser ltUserByModifiedBy,
			LtIdentifyingNumber ltIdentifyingNumber, LtUser ltUserByCreateBy,
			Date createDate, Date modifiedDate) {
		this.id = id;
		this.sourceCode = sourceCode;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.ltIdentifyingNumber = ltIdentifyingNumber;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "SOURCE_CODE_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "compId", column = @Column(name = "LT_IDENTIFYING_NUMBER_ID", nullable = false, precision = 22, scale = 0)) })
	public CompositeId getId() {
		return this.id;
	}

	public void setId(CompositeId id) {
		this.id = id;
	}

	@MapsId(value="id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOURCE_CODE_ID", nullable = false, insertable = false, updatable = false)
	public SourceCode getSourceCode() {
		return this.sourceCode;
	}

	public void setSourceCode(SourceCode sourceCode) {
		this.sourceCode = sourceCode;
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
	@JoinColumn(name = "LT_IDENTIFYING_NUMBER_ID", nullable = false, insertable = false, updatable = false)
	public LtIdentifyingNumber getLtIdentifyingNumber() {
		return this.ltIdentifyingNumber;
	}

	public void setLtIdentifyingNumber(LtIdentifyingNumber ltIdentifyingNumber) {
		this.ltIdentifyingNumber = ltIdentifyingNumber;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATE_BY", nullable = false)
	public LtUser getLtUserByCreateBy() {
		return this.ltUserByCreateBy;
	}

	public void setLtUserByCreateBy(LtUser ltUserByCreateBy) {
		this.ltUserByCreateBy = ltUserByCreateBy;
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