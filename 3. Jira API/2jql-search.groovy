package com.scitotec.training

import com.atlassian.jira.bc.issue.search.SearchService
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.jql.parser.JqlQueryParser
import com.atlassian.jira.web.bean.PagerFilter

def jql = "project = TEST"
def userKey = "admin"

def userManager = ComponentAccessor.getUserManager()
def queryParser = ComponentAccessor.getComponent(JqlQueryParser)
def searchService = ComponentAccessor.getComponent(SearchService)

def user = userManager.getUserByKey(userKey)
def query = queryParser.parseQuery(jql)

def results = searchService.search(user, query, PagerFilter.unlimitedFilter)

results.total
