package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.web.model.PageModel;

/**
 * 
 * @author Mikhel Adun
 *
 */
@Component
public class WorkQueueModel implements PageModel {

    List<LtLeadSubject> workQueueItems;

    public List<LtLeadSubject> getWorkQueueItems() {
        return workQueueItems;
    }

    public void setWorkQueueItems(List<LtLeadSubject> workQueueItems) {
        this.workQueueItems = workQueueItems;
    } 
}
