package com.siemens.bt.jazz.services.WorkItemBulkMover.bulkMover.models;

import com.siemens.bt.jazz.services.WorkItemBulkMover.rtc.models.WorkItem;

public class AffectedWorkItem {
    private WorkItem workItem;
    private String chosen;
    private boolean isRequired;

    public AffectedWorkItem(WorkItem workItem) {
        this(workItem, false);
    }

    public AffectedWorkItem(WorkItem workItem, boolean isRequired) {
        this.workItem = workItem;
        this.isRequired = isRequired;
        this.chosen = "nothing";
    }

    public WorkItem getWorkItem() {
        return workItem;
    }


    public String getMappedIdentifier() {
        return chosen;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof AffectedWorkItem) {
            AffectedWorkItem el = (AffectedWorkItem) object;
            return this.workItem.getId() == el.getWorkItem().getId();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(workItem.getId());
    }
}
