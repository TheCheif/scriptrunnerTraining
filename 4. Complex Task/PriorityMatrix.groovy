package com.scitotec

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.config.ConstantsManager
import com.atlassian.jira.issue.priority.Priority

class PriorityMatrix {
    private static urgencies = ["Sofort", "Hoch", "Mittel", "Niedrig"]
    private static impacts = ["Niedrig", "Mittel", "Hoch", "Sehr Hoch"]
    private static List<Tuple3<String, String, String>> matrix = [
        new Tuple3("Sofort", "Niedrig", "Wichtig"),
        new Tuple3("Hoch", "Niedrig", "Normal"),
        new Tuple3("Mittel", "Niedrig", "Gering"),
        new Tuple3("Niedrig", "Niedrig", "Gering"),

        new Tuple3("Sofort", "Mittel", "Kritisch"),
        new Tuple3("Hoch", "Mittel", "Wichtig"),
        new Tuple3("Mittel", "Mittel", "Normal"),
        new Tuple3("Niedrig", "Mittel", "Gering"),

        new Tuple3("Sofort", "Hoch", "Blocker"),
        new Tuple3("Hoch", "Hoch", "Kritisch"),
        new Tuple3("Mittel", "Hoch", "Wichtig"),
        new Tuple3("Niedrig", "Hoch", "Normal"),

        new Tuple3("Sofort", "Sehr Hoch", "Blocker"),
        new Tuple3("Hoch", "Sehr Hoch", "Blocker"),
        new Tuple3("Mittel", "Sehr Hoch", "Kritisch"),
        new Tuple3("Niedrig", "Sehr Hoch", "Wichtig"),
    ]

    private PriorityMatrix() { assert matrix.size() == urgencies.size() * impacts.size() }

    static Priority getPriorityByUrgencyAndImpact(String urgency, String impact) {
        assert matrix.size() == urgencies.size() * impacts.size()
        assert urgencies.contains(urgency)
        assert impacts.contains(impact)

        def priorityTuple = matrix.find {
            it.first == urgency && it.second == impact
        }

        assert priorityTuple != null

        ConstantsManager constantsManager = ComponentAccessor.getConstantsManager()

        def priorities = constantsManager.getPriorities()
        def prio = priorities.find { it.getName() == priorityTuple.v3 }

        assert prio != null

        return prio
    }
}
