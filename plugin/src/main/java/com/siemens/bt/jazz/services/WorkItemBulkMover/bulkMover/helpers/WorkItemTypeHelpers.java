package com.siemens.bt.jazz.services.WorkItemBulkMover.bulkMover.helpers;

import com.ibm.team.process.common.IProjectAreaHandle;
import com.ibm.team.repository.common.TeamRepositoryException;
import com.ibm.team.workitem.common.model.IWorkItem;
import com.ibm.team.workitem.common.model.IWorkItemType;
import com.ibm.team.workitem.service.IWorkItemServer;
import org.eclipse.core.runtime.IProgressMonitor;

import java.util.List;

public final class WorkItemTypeHelpers {

    public static void setWorkItemType(IWorkItem sourceWorkItem, IWorkItem targetWorkItem, String oldWorkItemTypeId, String newWorkItemTypeId,
                                       IWorkItemServer workItemServer, IProgressMonitor monitor) throws TeamRepositoryException {
        if(!oldWorkItemTypeId.equals(newWorkItemTypeId)) {
            IProjectAreaHandle sourcePa = sourceWorkItem.getProjectArea();
            IProjectAreaHandle targetPa = targetWorkItem.getProjectArea();
            IWorkItemType oldType = workItemServer.findWorkItemType(sourcePa, oldWorkItemTypeId, monitor);
            IWorkItemType newType = workItemServer.findWorkItemType(targetPa, newWorkItemTypeId, monitor);
            if (newType != null) {
                workItemServer.updateWorkItemType(targetWorkItem, newType, oldType, monitor);
            } else {
                throw new TeamRepositoryException("Target work item of type '" + newWorkItemTypeId + "' does not exist");
            }
        }
    }

    public static List<IWorkItemType> getWorkItemTypes(IProjectAreaHandle pa, IWorkItemServer workItemServer, IProgressMonitor monitor) throws TeamRepositoryException {
        List<IWorkItemType> workItemTypes = workItemServer.findCachedWorkItemTypes(pa);
        if (workItemTypes == null) {
            workItemTypes = workItemServer.findWorkItemTypes(pa, monitor);
        }
        return workItemTypes;
    }
}
