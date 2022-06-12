package basic.of.javastudy.businessruleengine;

public class Report {
    private final ConditionalAction conditionalAction;
    private final Facts facts;
    private final boolean isPositive;
    private final Condition condition;

    public Report(ConditionalAction conditionalAction, Facts facts, boolean isPositive) {
        this.conditionalAction = conditionalAction;
        this.facts = facts;
        this.isPositive = isPositive;
        this.condition = null;
    }

    public Report(Condition condition, Facts facts, boolean isPositive) {
        this.conditionalAction = null;
        this.condition = condition;
        this.facts = facts;
        this.isPositive = isPositive;
    }

    public ConditionalAction getConditionalAction() {
        return conditionalAction;
    }

    public Facts getFacts() {
        return facts;
    }

    public boolean isPositive() {
        return isPositive;
    }

    @Override
    public String toString() {
        return "Report{" +
                "conditionalAction=" + conditionalAction +
                ", facts=" + facts +
                ", isPositive=" + isPositive +
                '}';
    }
}
