package co.adun.mvnejb3jpa.web.model;


import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.web.model.PageModel;

@Component
public class ValueModel implements PageModel {

    public String value;
    public Object objRef;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setRef(Object objRef) {
	this.objRef = objRef;
    }
    public Object getRef() {
	return this.objRef;
    }    
}
