/**
 * @Author - Kalyan
 */

package co.adun.mvnejb3jpa.persistence.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table (name = "LT_SUBJECT_ALIAS")
@SequenceGenerator(name = "LT_SUBJECT_ALIAS_SEQ", sequenceName = "LT_SUBJECT_ALIAS_SEQ", allocationSize = 1, initialValue = 1)
public class LtSubjectAlias {

	private Long id;
	private LtSubject ltSubject;
	private String lastname;
	private String firstname;
	private Date birthDate;
	private String aliasComment;
	private Date createDate;
	private LtUser createBy;
	private Date modifiedDate;
	private LtUser modifiedBy;
	
	private Set<LtSubjectAliasSource> ltSubjectAliasSources = new HashSet<LtSubjectAliasSource>(0);
	
	public LtSubjectAlias(){}
	
	@Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LT_SUBJECT_ALIAS_SEQ")
    public Long getId() {
	return this.id;
    }
    public void setId(Long id) {
	this.id = id;
    }
    
    @ManyToOne
    @JoinColumn( name = "LT_SUBJECT_ID" , nullable = false )
    public LtSubject getLtSubject(){
    	return this.ltSubject;
    }
    
    public void setLtSubject(LtSubject ltSubject){
    	this.ltSubject = ltSubject;
    }
    
    @Column( name = "LASTNAME" , nullable = false)
    public String getLastname(){
    	return this.lastname;
    }
    
    public void setLastname(String lastname) {
    	this.lastname = lastname;
    }
    
    @Column(name = "FIRSTNAME" , nullable = false)
    public String getFirstname(){
    	return this.firstname;
    }
    
    public void setFirstname(String firstname){
    	this.firstname = firstname;
    }
    
    @Column( name = "BIRTH_DATE" , nullable = true)
    public Date getBirthDate(){
    	return this.birthDate;
    }
    
    public void setBirthDate(Date birthDate){
    	this.birthDate = birthDate;
    }
    
    @Column( name = "ALIAS_COMMENT" , nullable = true)
    public String getAliasComment(){
    	return this.aliasComment;
    }
    
    public void setAliasComment(String aliasComment){
    	this.aliasComment = aliasComment;
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
    
    @OneToMany( mappedBy = "ltSubjectAlias", cascade={CascadeType.PERSIST,CascadeType.MERGE , CascadeType.REFRESH})
    public Set<LtSubjectAliasSource> getLtSubjectAliasSources(){
    	return this.ltSubjectAliasSources;
    }
    
    public void setLtSubjectAliasSources(Set<LtSubjectAliasSource> ltSubjectAliasSources){
    	this.ltSubjectAliasSources = ltSubjectAliasSources;
    }
       
}
