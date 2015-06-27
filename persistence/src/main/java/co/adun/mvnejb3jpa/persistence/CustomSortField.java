package co.adun.mvnejb3jpa.persistence;

import java.io.Serializable;

public class CustomSortField implements Serializable {
    private static final long serialVersionUID = 1L;

    private String propertyName;
    private String ordering;

    public static final String ASCENDING = "ASCENDING";
    public static final String DESCENDING = "DESCENDING";

    public CustomSortField(String propertyName, String ordering) {
	this.propertyName = propertyName;
	this.ordering = ordering;
    }

    public String getPropertyName() {
	return propertyName;
    }

    public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
    }

    public String getOrdering() {
	return ordering;
    }

    public void setOrdering(String ordering) {
	this.ordering = ordering;
    }

    @Override
    public String toString() {
	return "CustomSortField [propertyName=" + propertyName + ", ordering=" + ordering + "]";
    }
}
