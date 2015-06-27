/**
 * @Author - Kalyan
 */

package co.adun.mvnejb3jpa.persistence.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table (name = "LT_SUBJECT_ALIAS_SOURCE")
public class LtSubjectAliasSource extends BaseEntity{
	private CompositeId id;
	private SourceCode sourceCode;
	private LtSubjectAlias ltSubjectAlias;
	private Date createDate;
	private LtUser createBy;
	private Date modifiedDate;
	private LtUser modifiedBy;
	
	
	 @EmbeddedId
		@AttributeOverrides({
				@AttributeOverride(name = "id", column = @Column(name = "SOURCE_CODE_ID", nullable = false, precision = 22, scale = 0)),
				@AttributeOverride(name = "compId", column = @Column(name = "LT_SUBJECT_ALIAS_ID", nullable = false, precision = 22, scale = 0)) })
	public CompositeId getId() {
		return this.id;
	}
	
	public void setId(CompositeId id){
		this.id = id;
	}
	
	@MapsId("id")
	@ManyToOne
	@JoinColumn ( name = "SOURCE_CODE_ID" , referencedColumnName = "ID")
	public SourceCode getSourceCode(){
		return this.sourceCode;
	}
	
	public void setSourceCode(SourceCode sourceCode){
		this.sourceCode = sourceCode;
	}
	
	@MapsId("compId")
	@ManyToOne
	@JoinColumn( name = "LT_SUBJECT_ALIAS_ID", referencedColumnName = "ID")
	public LtSubjectAlias getLtSubjectAlias(){
		return this.ltSubjectAlias;
	}
	
	public void setLtSubjectAlias(LtSubjectAlias ltSubjectAlias){
		this.ltSubjectAlias = ltSubjectAlias;
	}
	
	@Column( name = "CREATE_DATE" , nullable = false)
	    public Date getCreateDate(){
	    	return this.createDate;
	    }
	    
	    public void setCreateDate(Date createDate){
	    	this.createDate = createDate;
	    }
	    
	    @ManyToOne
	    @JoinColumn( name = "CREATE_BY", nullable = false) 
	    public LtUser getCreateBy(){
	    	return this.createBy;
	    }
	    
	    public void setCreateBy(LtUser createBy){
	    	this.createBy = createBy;
	    }
	    
	    @Column( name = "MODIFIED_DATE", nullable = false)
	    public Date getModifiedDate(){
	    	return this.modifiedDate;
	    }
	    
	    public void setModifiedDate(Date modifiedDate){
	    	this.modifiedDate = modifiedDate;
	    }
	    
	    @ManyToOne
	    @JoinColumn ( name = "MODIFIED_BY" , nullable = false)
	    public LtUser getModifiedBy(){
	    	return this.modifiedBy;
	    }
	    
	    public void setModifiedBy(LtUser modifiedBy){
	    	this.modifiedBy = modifiedBy;
	    }

}
