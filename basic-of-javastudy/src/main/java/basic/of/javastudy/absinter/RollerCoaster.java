package basic.of.javastudy.absinter;

import java.util.Random;

public class RollerCoaster implements Attraction{

    @Override
    public void startAttraction() {
        System.out.println("롤러 코스터를 시작합니다.");
        this.run();

        long runningTime = ((long) (Math.random() * 7) * 1000) + Attraction.MIN_RUNNING_TIME_SEC;
        if (runningTime > Attraction.MAX_RUNNING_TIME_SEC) {
            runningTime = Attraction.MAX_RUNNING_TIME_SEC;
        }

        try {
            System.out.printf("롤러 코스터가 %.3f 초간 운행됩니다.\n", runningTime / 1000.0);
            Thread.sleep(runningTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.endAttraction();
    }

    @Override
    public void endAttraction() {
        System.out.println("롤러 코스터가 종료되었습니다.");
    }

    @Override
    public void run() {
        System.out.println("롤러 코스터 신나게 달리는 중!!!!");
    }
}
