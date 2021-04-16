package com.aimprosoft.scripts;

import org.alfresco.service.cmr.workflow.WorkflowService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GetReview extends DeclarativeWebScript {

    Log logger = LogFactory.getLog(GetReview.class);

    private WorkflowService workflowService;

    public void setWorkflowService(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

        final String id = req.getParameter("id");
        final String action = req.getParameter("action");

        if (id == null || action == null) {
            logger.debug("Email, ID, action, or secret not set");
            status.setCode(400);
            status.setMessage("Required data has not been provided");
            status.setRedirect(true);
        }

        Map<String, Object> model = new HashMap<>();


        Map<QName, Serializable> props = new HashMap<>();
        props.put(QName.createQName("http://www.vhoncharov.org/model/workflow/1.0", "approveRejectOutcome"), action);
        try {
            workflowService.updateTask(id, props, null, null);
            workflowService.endTask(id, action);
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }

        return model;
    }
}