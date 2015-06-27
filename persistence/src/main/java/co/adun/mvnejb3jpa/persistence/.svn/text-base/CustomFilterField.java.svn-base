package co.adun.mvnejb3jpa.persistence;

import java.io.Serializable;

public class CustomFilterField implements Serializable
{
        private static final long serialVersionUID = 1L;

        private Object filterValue;
        private String propertyName;
        
        public CustomFilterField(String propertyName, Object filterValue)
        {
            setPropertyName(propertyName);
            setFilterValue(filterValue);
        }
        
        public String getPropertyName()
        {
                return propertyName;
        }
        
        public void setPropertyName(String propertyName)
        {
                this.propertyName = propertyName;
        }
        
        public Object getFilterValue()
        {
                return filterValue;
        }
        
        public void setFilterValue(Object filterValue)
        {
                this.filterValue = filterValue;
        }

        @Override
        public String toString()
        {
                return "CustomFilterField [filterValue=" + filterValue + ", propertyName=" + propertyName + "]";
        }

}