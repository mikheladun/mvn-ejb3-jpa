/**
 * @Author - Kalyan
 */

package co.adun.mvnejb3jpa.persistence.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "LT_SUBJECT_ASSOCIATED")
public class LtAssociatedSubject extends BaseEntity {
	private CompositeId id;
	private LtSubject ltSubjectAssociate;
	private LtSubject ltSubject;
	private String assocSubjectComment;
	private RelationshipCode relationshipCode;
	private Date createDate;
	private LtUser createBy;
	private Date modifiedDate;
	private LtUser modifiedBy;

	public LtAssociatedSubject() {
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
	        @AttributeOverride(name = "compId", column = @Column(name = "LT_SUBJECT_ID", nullable = false)) })
	public CompositeId getId() {
		return this.id;
	}

	public void setId(CompositeId id) {
		this.id = id;
	}

	@MapsId("compId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LT_SUBJECT_ID", nullable = false)
	public LtSubject getLtSubject() {
		return this.ltSubject;
	}

	public void setLtSubject(LtSubject ltSubject) {
		this.ltSubject = ltSubject;
	}

	@MapsId("id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", nullable = false)
	public LtSubject getLtSubjectAssociate() {
		return this.ltSubjectAssociate;
	}

	public void setLtSubjectAssociate(LtSubject ltSubjectAssociate) {
		this.ltSubjectAssociate = ltSubjectAssociate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RELATIONSHIP_CODE_ID", nullable = false)
	public RelationshipCode getRelationshipCode() {
		return this.relationshipCode;
	}

	@Column(name = "SUBJECT_ASSOCIATED_COMMENT", nullable = true)
	public String getAssocSubjectComment() {
		return this.assocSubjectComment;
	}

	public void setAssocSubjectComment(String assocSubjectComment) {
		this.assocSubjectComment = assocSubjectComment;
	}

	public void setRelationshipCode(RelationshipCode relationshipCode) {
		this.relationshipCode = relationshipCode;
	}

	@Column(name = "CREATE_DATE", nullable = false)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATE_BY", nullable = false)
	public LtUser getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(LtUser createBy) {
		this.createBy = createBy;
	}

	@Column(name = "MODIFIED_DATE", nullable = false)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFIED_BY", nullable = false)
	public LtUser getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(LtUser modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
