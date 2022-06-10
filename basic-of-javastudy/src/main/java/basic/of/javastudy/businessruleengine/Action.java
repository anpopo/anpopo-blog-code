package basic.of.javastudy.businessruleengine;

/**
 * facts 의 특성을 조건 삼아 액션을 수행할 수 있게 facts 에 의존성이 있다
 */
@FunctionalInterface
public interface Action {

    void perform(Facts facts);
}
