package co.adun.mvnejb3jpa.persistence.entity;

// Generated May 5, 2013 9:04:21 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * LtSubjectContact generated by hbm2java
 */
@Entity
@Table(name = "LT_SUBJECT_TRAVEL")
@SequenceGenerator(name = "LT_SUBJECT_TRAVEL_SEQ", sequenceName = "LT_SUBJECT_SEQ", allocationSize = 1, initialValue = 1)
public class LtSubjectTravel extends BaseEntity {

	private Long id;
	private LtSubject ltSubject;
	private String flightNumber;
	private String carrier;
	private String travelComment;
	private Date travelDate;
	private TravelDirectionCode travelDirectionCode;
	private LtUser ltUserByCreateBy;
	private LtUser ltUserByModifiedBy;
	private Date createDate;
	private Date modifiedDate;
	private Set<LtSubjectTravelSource> ltSubjectTravelSources = new HashSet<LtSubjectTravelSource>(0);

	public LtSubjectTravel() {
	}

	public LtSubjectTravel(Long id, LtUser ltUserByModifiedBy, LtSubject ltSubject, TravelDirectionCode travelDirectionCode, String flightNumber, String carrier,
	        LtUser ltUserByCreateBy, Date createDate, Date modifiedDate) {
		this.id = id;
		this.ltSubject = ltSubject;
		this.flightNumber = flightNumber;
		this.carrier = carrier;
		this.travelDirectionCode = travelDirectionCode;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.createDate = createDate;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public LtSubjectTravel(Long id, LtUser ltUserByModifiedBy, LtSubject ltSubject, String travelComment, Date travelDate, TravelDirectionCode travelDirectionCode,
	        String flightNumber, String carrier, LtUser ltUserByCreateBy, Date createDate, Date modifiedDate) {
		this.id = id;
		this.ltSubject = ltSubject;

		this.flightNumber = flightNumber;
		this.travelDate = travelDate;
		this.travelComment = travelComment;
		this.carrier = carrier;
		this.travelDirectionCode = travelDirectionCode;
		this.ltUserByCreateBy = ltUserByCreateBy;
		this.createDate = createDate;
		this.ltUserByModifiedBy = ltUserByModifiedBy;
		this.modifiedDate = modifiedDate;
	}

	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LT_SUBJECT_TRAVEL_SEQ")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LT_SUBJECT_ID")
	public LtSubject getLtSubject() {
		return this.ltSubject;
	}

	public void setLtSubject(LtSubject ltSubject) {
		this.ltSubject = ltSubject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAVEL_DIRECTION_CODE_ID")
	public TravelDirectionCode getTravelDirectionCode() {
		return this.travelDirectionCode;
	}

	public void setTravelDirectionCode(TravelDirectionCode travelDirectionCode) {
		this.travelDirectionCode = travelDirectionCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATE_BY", nullable = false)
	public LtUser getLtUserByCreateBy() {
		return this.ltUserByCreateBy;
	}

	public void setLtUserByCreateBy(LtUser ltUserByCreateBy) {
		this.ltUserByCreateBy = ltUserByCreateBy;
	}

	@Column(name = "TRAVEL_COMMENT", length = 4000)
	public String getTravelComment() {
		return this.travelComment;
	}

	public void setTravelComment(String travelComment) {
		this.travelComment = travelComment;
	}

	@Column(name = "FLIGHT_NUMBER", nullable = false)
	public String getFlightNumber() {
		return this.flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Column(name = "CARRIER", nullable = false)
	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@Column(name = "TRAVEL_DATE", nullable = true)
	public Date getTravelDate() {
		return this.travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ltSubjectTravel", cascade = { CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	public Set<LtSubjectTravelSource> getLtSubjectTravelSources() {
		return this.ltSubjectTravelSources;
	}

	public void setLtSubjectTravelSources(Set<LtSubjectTravelSource> ltSubjectTravelSources) {
		this.ltSubjectTravelSources = ltSubjectTravelSources;
	}

}