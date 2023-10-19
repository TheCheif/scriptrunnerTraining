package com.scitotec.training
//! important

// --- Configuration
def issueKey = "TEST-12"
def userKey = "admin"
def commentToBeAdded = "Das ist ein neuer Kommentar"
def sendNotifications = false

// --- Code DO NOT MODIFY
def issue = Helpers.getIssueByKey(issueKey)
def user = Helpers.getUserByKey(userKey)

if (issue != null && user != null) {
    def comment = Helpers.createComment(issue, user, commentToBeAdded, sendNotifications)
    comment != null ? "Comment created" : "Comment could not be created"
} else {
    "Issue or User missing"
}
