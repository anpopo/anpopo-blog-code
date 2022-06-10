package basic.of.javastudy.businessruleengine;

public class BusinessRuleEngineMain {

    public static void main(String[] args) {

        final Facts facts = new Facts();
        facts.addFact("name", "anpopo");
        facts.addFact("rank", "CTO");

        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);

        businessRuleEngine.addRule(f -> {
            final String rank = facts.getFact("rank");
            if ("CTO".equals(rank)) {
                String name = facts.getFact("name");
                System.out.printf("Hi! Have a good day %s\n", name);
            }
        });

        businessRuleEngine.run();
    }
}
