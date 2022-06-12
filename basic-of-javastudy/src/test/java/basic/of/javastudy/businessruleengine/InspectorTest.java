package basic.of.javastudy.businessruleengine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class InspectorTest {
    @Test
    void inspectOneConditionalEvaluatesTrue() {
        Facts facts = new Facts();
        facts.addFact("rank", "CTO");
        ConditionalAction conditionalAction = new RankConditionAction();
        Inspector inspector = new Inspector(conditionalAction);

        List<Report> reportList = inspector.inspect(facts);
        assertEquals(1, reportList.size());
        assertTrue(reportList.get(0).isPositive());

    }

    private static class RankConditionAction implements ConditionalAction {

        @Override
        public boolean evaluate(Facts facts) {
            return "CTO".equals(facts.getFact("rank"));
        }

        @Override
        public void perform(Facts facts) {
            throw new UnsupportedOperationException();
        }
    }

    @Test
    void inspectRuleAndCondition() {
        Facts facts = new Facts();
        facts.addFact("rank", "CTO");
        Condition condition = new RankCondition();

        Inspector inspector = new Inspector(condition);
        assertTrue(inspector.inspectConditions(facts).get(0).isPositive());
    }

    private static class RankCondition implements Condition {

        @Override
        public boolean evaluate(Facts facts) {
            return "CTO".equals(facts.getFact("rank"));
        }

    }
}