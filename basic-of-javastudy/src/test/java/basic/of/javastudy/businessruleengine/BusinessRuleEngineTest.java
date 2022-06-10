package basic.of.javastudy.businessruleengine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BusinessRuleEngineTest {

    @Test
    void shouldHaveNoRulesInitially() {
        Facts mockFacts = mock(Facts.class);

        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
        assertEquals(0, businessRuleEngine.count());
    }


    @Test
    void shouldAddTwoActions() {
        Facts mockFacts = mock(Facts.class);

        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
        businessRuleEngine.addRule((Facts facts) -> {});
        businessRuleEngine.addRule((Facts facts) -> {});

        assertEquals(2, businessRuleEngine.count());
    }

    @Test
    void shouldExecuteOneAction() {
        Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
        Rule mockRule = mock(Rule.class);


        businessRuleEngine.addRule(mockRule);
        businessRuleEngine.run();

        verify(mockRule).perform(mockFacts);
    }

    @Test
    void shouldPerformAnActionWithFacts() {
        Rule mockRule = mock(Rule.class);
        Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        businessRuleEngine.addRule(mockRule);
        businessRuleEngine.run();

        verify(mockRule).perform(mockFacts);
    }

}