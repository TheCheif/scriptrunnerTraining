package com.scitotec.training

import com.atlassian.jira.user.ApplicationUser
import com.atlassian.plugin.PluginParseException
import com.atlassian.plugin.web.Condition

class BannerCondition implements Condition {

    private final String groupName = "jira-administrators"

    @Override
    void init(Map<String, String> map) throws PluginParseException { }

    @Override
    boolean shouldDisplay(Map<String, Object> map) {
        def currentUser = map.user as ApplicationUser
        def groups = Helpers.getGroupsForUser(currentUser)
        return groups.contains(groupName)
    }
}