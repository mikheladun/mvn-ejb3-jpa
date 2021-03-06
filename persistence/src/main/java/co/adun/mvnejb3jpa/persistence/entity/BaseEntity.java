package co.adun.mvnejb3jpa.persistence.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import co.adun.mvnejb3jpa.persistence.Persistable;

/**
 * A base class, placeholder or replacement for JPA persistable entity
 * 
 * @author Mikhel Adun
 * @param <T>
 */
@MappedSuperclass
@Embeddable
public class BaseEntity implements Persistable, Serializable {
    private static final long serialVersionUID = 1L;

    protected static final long UNSAVED_ID = -1L;

//    boolean saved = false;

    public BaseEntity() {
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorName")
//    @SequenceGenerator(name = "generatorName", sequenceName = "HibernateIdGenSeq", allocationSize = 1)
//    @Column(name = "id")
//    private Long id = UNSAVED_ID;

    /*
     * (non-Javadoc) co.adun.mvnejb3jpa.persistence.Persistable#getId()
     */
//    public Long getId() {
//	return id;
//    }
//
//    /*
//     * (non-Javadoc) co.adun.mvnejb3jpa.persistence.Persistable#setId()
//     */
//    public void setId(Long id) {
//	this.id = id;
//    }

    /**
     * Peek at the ID without generating one, if unset
     * 
     * @return
     */
//    public final Object peekId() {
//	return id;
//    }

    /**
     * Uses the same implementation of hashCode() as the java.lang.Long class, using the long id. From that classes javadoc: The result is the exclusive OR of the two halves of the
     * primitive long value represented by this Long object. That is, the hashcode is the value of the expression: (int)(this.longValue()^(this.longValue()>>>32)) Or in our case,
     * the value of the expression: (int)(this.id^(this.id>>>32)) Note that this method is marked as final because subclasses should never alter this behavior.
     */
//    @Override
//    public final int hashCode() {
//	return id.intValue();
//    }

    /**
     * Returns true if 1) The object references are equal: this == obj 2) The objects have the same class and id, provided the id is not UNSAVED_ID (the default id) Otherwise, this
     * method will always return false, regardless of any other property values. Note that this method is marked as final because subclasses should never alter this behavior.
     */
//    @Override
//    public final boolean equals(Object obj) {
//	if (this == obj) {
//	    return true;
//	}
//	if (obj == null) {
//	    return false;
//	}
//
//	if (obj.getClass() == getClass()) {
//	    return id != UNSAVED_ID && ((BaseEntity) obj).id == id;
//	}
//
//	return false;
//    }
//
//    @Override
//    @Transient
//    public boolean getIsUnsaved() {
//	return !saved;
//    }
//
//    @Transient
//    @XmlTransient
//    public boolean isSaved() {
//	return saved;
//    }
//
//    @Transient
//    public void setSaved(boolean saved) {
//	this.saved = saved;
//    }

    /*
     * (non-Javadoc)
     * @see co.adun.mvnejb3jpa.persistence.Persistable#toString()
     */
//    @Override
//    public String toString() {
//	StringBuilder builder = new StringBuilder();
//	builder.append(getClass().getName() + "@");
//	builder.append(System.identityHashCode(this) + " [");
//	builder.append(id);
//	builder.append("]");
//	return builder.toString();
//    }

    /*
     * (non-Javadoc)
     * @see co.adun.mvnejb3jpa.persistence.Persistable#canBeRemoved()
     */
//    @Override
//    public boolean canBeRemoved() {
//	return true;
//    }
}