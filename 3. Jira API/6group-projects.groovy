package com.scitotec.training

def jql = "component = TESTKOMPONENTE"
def userName = "admin"

def user = Helpers.getUserByKey(userName)
def issues = Helpers.searchIssuesByJQL(jql, user)

issues.groupBy { 
    it.projectObject.name 
}.keySet()