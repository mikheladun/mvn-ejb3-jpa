package co.adun.mvnejb3jpa.persistence.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Mikhel Adun
 * 
 */
@XmlRootElement
@Embeddable
public class CompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5055641991595869684L;
    private Long id;
    private Long compId;

    public Long getId() {
	return id;
    }

    @XmlElement
    public void setId(Long id) {
	this.id = id;
    }

    public Long getCompId() {
	return compId;
    }

    @XmlElement
    public void setCompId(Long compId) {
	this.compId = compId;
    }

    public boolean equals(Object other) {
	if ((this == other))
	    return true;
	if ((other == null))
	    return false;
	if (!(other instanceof CompositeId))
	    return false;
	CompositeId castOther = (CompositeId) other;

	return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
		&& ((this.getCompId() == castOther.getCompId()) || (this.getCompId() != null && castOther.getCompId() != null && this.getCompId()
			.equals(castOther.getCompId())));
    }

    public int hashCode() {
	int result = 17;

	result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
	result = 37 * result + (getCompId() == null ? 0 : this.getCompId().hashCode());
	return result;
    }

}
