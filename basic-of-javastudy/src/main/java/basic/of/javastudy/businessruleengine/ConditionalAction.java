package basic.of.javastudy.businessruleengine;

/**
 * ISP (Interface Segregation Principle) 을 위배한 인터페이스 설계
 *
 * 해당 인터페이스를 구현하는 모든 클래스에서 반드시 모든 메서드를 사용하는 것이 아니기 때문에 불필요한 의존성이 발생한다.
 * 이러한 경우 evaluate 와 perform 을 분리시켜 각각의 인터페이스로 만드는 것이 ISP 를 지키는 것이다.
 */
public interface ConditionalAction {

    boolean evaluate(Facts facts);

    void perform(Facts facts);
}
