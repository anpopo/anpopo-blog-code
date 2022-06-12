package basic.of.javastudy.businessruleengine;

@FunctionalInterface
public interface Rule {
    void perform(Facts facts);
}
