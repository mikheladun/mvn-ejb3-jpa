package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import co.adun.mvnejb3jpa.persistence.entity.ExampleEntity;
import co.adun.mvnejb3jpa.web.model.PageModel;

public class ExampleModel implements PageModel {

    ExampleEntity entity;
    List<ExampleEntity> entities;

    public ExampleEntity getEntity() {
	return entity;
    }

    public void setEntity(ExampleEntity entity) {
	this.entity = entity;
    }

    public List<ExampleEntity> getEntities() {
	return entities;
    }

    public void setEntities(List<ExampleEntity> entities) {
	this.entities = entities;
    }

}
