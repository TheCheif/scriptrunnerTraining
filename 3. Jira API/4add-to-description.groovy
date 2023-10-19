package com.scitotec.training

// --- Configuration
def jql = "project in (\"TEST\", \"TEST1\")"
def userKey = "admin"
def resolutionName = "Done"
def toAdd = "Dieser Vorgang wird in wenigen Tagen automatisch geschlossen"

// --- Code DO NOT MODIFY
def user = Helpers.getUserByKey(userKey)
def issues = Helpers.searchIssuesByJQL(jql, user)

issues.forEach {
    Helpers.addToIssueDescription(it, toAdd, user)
    Helpers.createComment(it, user, "Beschreibung wurde durch ein Skript bearbeitet", true)
}
