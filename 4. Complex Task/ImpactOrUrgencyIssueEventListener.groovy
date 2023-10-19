package com.scitotec

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.event.issue.IssueEvent
import com.atlassian.jira.event.type.EventDispatchOption
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.priority.Priority

def issueEvent = event as IssueEvent
MutableIssue issue = (MutableIssue) issueEvent.getIssue()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def issueManager = ComponentAccessor.getIssueManager()

def allowedFields = ["Auswirkung", "Dringlichkeit"]

def change = issueEvent.getChangeLog().getRelated("ChildChangeItem").find {
    it.get("fieldtype") == "custom" && allowedFields.contains(it.get("field"))
}

if (change) {
    def urgencyFields = customFieldManager.getCustomFieldObjectsByName(allowedFields[1])
    def impactFields = customFieldManager.getCustomFieldObjectsByName(allowedFields[0])
    assert urgencyFields.size() == 1
    assert impactFields.size() == 1

    def urgency = issueEvent.getIssue().getCustomFieldValue(urgencyFields[0]) as String
    def impact = issueEvent.getIssue().getCustomFieldValue(impactFields[0]) as String

    Priority priority = PriorityMatrix.getPriorityByUrgencyAndImpact(urgency, impact)

    if (priority) {
        issue.setPriority(priority)
        issueManager.updateIssue(issueEvent.getUser(), issue, EventDispatchOption.DO_NOT_DISPATCH, false)
    }
}
