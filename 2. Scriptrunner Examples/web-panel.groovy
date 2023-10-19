// section: atl.jira.view.issue.right.context
import com.atlassian.jira.component.ComponentAccessor

def count = ComponentAccessor.getIssueManager().getIssueIdsForProject(context.get("project").id).size()
writer.write("<h1>In diesem Projekt befinden sich $count Vorgänge</h1>")