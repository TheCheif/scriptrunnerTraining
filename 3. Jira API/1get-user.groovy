package com.scitotec.training

import com.atlassian.jira.component.ComponentAccessor

def userManager = ComponentAccessor.getUserManager()
def user = userManager.getUserByKey("admin")

user?.emailAddress ?: "Not Found"

// currently logged in user
ComponentAccessor.getJiraAuthenticationContext().loggedInUser.emailAddress