package com.scitotec.training

import com.atlassian.jira.bc.issue.search.SearchService
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.event.type.EventDispatchOption
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.comments.Comment
import com.atlassian.jira.jql.parser.JqlQueryParser
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.web.bean.PagerFilter
import groovy.util.logging.Log4j

@Log4j
class Helpers {
    static Comment createComment(Issue issue, ApplicationUser user, String comment, Boolean sendNotifications) {
        def commentManager = ComponentAccessor.getCommentManager()
        commentManager.create(
                issue,
                user,
                comment,
                sendNotifications,
        )
    }

    static Issue getIssueByKey(String issueKey) {
        def issueManager = ComponentAccessor.getIssueManager()
        return issueManager.getIssueByCurrentKey(issueKey)
    }

    static ApplicationUser getUserByKey(String userKey) {
        def userManager = ComponentAccessor.getUserManager()
        return userManager.getUserByKey(userKey)
    }

    static List<Issue> searchIssuesByJQL(String jql, ApplicationUser user) {
        def queryParser = ComponentAccessor.getComponent(JqlQueryParser)
        def searchService = ComponentAccessor.getComponent(SearchService)
        def query = queryParser.parseQuery(jql)
        return searchService.search(user, query, PagerFilter.unlimitedFilter).results
    }

    static void addToIssueDescription(Issue issue, String toAdd, ApplicationUser user) {
        def issueService = ComponentAccessor.getIssueService()
        def issueInputParams = issueService.newIssueInputParameters()

        issueInputParams.description = issue.description + "\n" + toAdd
        def result = issueService.validateUpdate(
                user,
                issue.id,
                issueInputParams,
        )
        if (result.isValid()) {
            issueService.update(user, result, EventDispatchOption.DO_NOT_DISPATCH, false)
        } else {
            log.error("Could not add resolution to issue. Error: " + result.toString())
        }
    }

    static Collection<String> getGroupsForUser(ApplicationUser user) {
        def groupManager = ComponentAccessor.getGroupManager()
        return groupManager.getGroupNamesForUser(user)
    }
}
