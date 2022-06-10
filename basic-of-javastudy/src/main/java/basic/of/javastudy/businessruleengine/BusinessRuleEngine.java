package basic.of.javastudy.businessruleengine;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class BusinessRuleEngine {

    private final List<Rule> rules;
    private final Facts facts;

    public BusinessRuleEngine(Facts facts) {
        this.rules = new ArrayList<>();
        this.facts = facts;
    }

    public void addRule(final Rule rule) {
        rules.add(rule);
    }

    public int count() {
        return this.rules.size();
    }

    public void run() {
        this.rules.forEach(action -> action.perform(this.facts));
    }

}
