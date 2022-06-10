package basic.of.javastudy.businessruleengine;

@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
