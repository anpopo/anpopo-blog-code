package basic.of.javastudy.businessruleengine;

/**
 * 플루언트 api 의 핵심인 method chaining 방식을 이용해 사용자의 조건과 액션을 받아서 규칙을 생성하도록 빌더 패턴을 사용했다.
 */
public class RuleBuilder {
    private Condition condition;
    private Action action;

    private RuleBuilder(final Condition condition) {
        this.condition = condition;
    }

    public RuleBuilder when(final Condition condition) {
        return new RuleBuilder(condition);
    }

    public Rule then(final Action action) {
        return new DefaultRule(condition, action);
    }

}
