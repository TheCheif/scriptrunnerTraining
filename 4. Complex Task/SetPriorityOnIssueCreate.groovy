package com.scitotec

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.event.type.EventDispatchOption
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.user.util.UserManager

MutableIssue issue = (MutableIssue) event.issue

CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager()
IssueManager issueManager = ComponentAccessor.getIssueManager()
UserManager userManager = ComponentAccessor.getUserManager()

def urgencyFieldName = "Dringlichkeit"
def impactFieldName = "Auswirkung"
def adminUser = "admin"

def urgencyFields = customFieldManager.getCustomFieldObjectsByName(urgencyFieldName)
def impactFields = customFieldManager.getCustomFieldObjectsByName(impactFieldName)

assert urgencyFields.size() == 1
assert impactFields.size() == 1

String urgencyValue = issue.getCustomFieldValue(urgencyFields[0])
String impactValue = issue.getCustomFieldValue(impactFields[0])

def priority = PriorityMatrix.getPriorityByUrgencyAndImpact(urgencyValue, impactValue)

def user = userManager.getUserByName(adminUser)
assert user != null

issue.setPriority(priority)
issueManager.updateIssue(user, issue, EventDispatchOption.DO_NOT_DISPATCH, false)
