package basic.of.javastudy.absinter;

public interface Attraction {

    // public static final 중 어느것을 생략해도 인터페이스에 정의된 변수는 상수로 인식 public static final 이 붙게 됩니다.
    long MIN_RUNNING_TIME_SEC = 4_000;
    public static final long MAX_RUNNING_TIME_SEC = 10_000;


    public abstract void startAttraction();

    // public abstract 중 어느것을 생략해도 인터페이스에 정의된 메소드는 컴파일시 자동으로 public abstract 가 붙게 됩니다.
    void endAttraction();

    void run();

    default void emergencyStop() {
        System.out.println("비상 정지를 합니다. 안전에 유의하세요.");
    }

    static void fix() {
        System.out.println("놀이기구를 수리합니다.");
    }
}