package co.adun.mvnejb3jpa.web.model;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.persistence.entity.RelationshipCode;

@Component
public class AssociatedLeadModel extends ValueModel {
    public RelationshipCode code;

    public RelationshipCode getRelationshipCode() {
	return code;
    }

    public void setRelationshipCode(RelationshipCode code) {
	this.code = code;
    }

}
