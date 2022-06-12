package basic.of.javastudy.businessruleengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 액션과 조건을 검사하기 위한 클래스
 *
 */
public class Inspector {
    private final List<ConditionalAction> conditionalActionList;
    private final List<Condition> conditions;

    public Inspector(final ConditionalAction... conditionalActions) {
        this.conditionalActionList = Arrays.asList(conditionalActions);
        conditions = new ArrayList<>();
    }

    public Inspector(final Condition... conditions) {
        this.conditions = Arrays.asList(conditions);
        this.conditionalActionList = new ArrayList<>();
    }

    public List<Report> inspect(final Facts facts) {
        final List<Report> reportList = new ArrayList<>();
        for (ConditionalAction conditionalAction : conditionalActionList) {
            final boolean evaluate = conditionalAction.evaluate(facts);
            reportList.add(new Report(conditionalAction, facts, evaluate));
        }
        return reportList;
    }

    public List<Report> inspectConditions(final Facts facts) {
        final List<Report> reportList = new ArrayList<>();
        for (Condition condition : conditions) {
            boolean evaluate = condition.evaluate(facts);
            reportList.add(new Report(condition, facts, evaluate));
        }
        return reportList;
    }
}
