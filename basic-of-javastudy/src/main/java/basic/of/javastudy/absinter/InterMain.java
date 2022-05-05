package basic.of.javastudy.absinter;

import java.util.List;

public class InterMain {
    public static void main(String[] args) {
        String rollerCoaster = "RollerCoaster";
        String viking = "Viking";
        List<String> attractionName = List.of(rollerCoaster, viking);

        for (int i = 0; i < attractionName.size(); i++) {
            Attraction attraction = AttractionFactory.of(attractionName.get(i));
            if (attraction != null ) {
                attraction.startAttraction();
            }
            System.out.println("=============================================");
        }

        for (int i = 0; i < attractionName.size(); i++) {
            Attraction attraction = AttractionFactory.of(attractionName.get(i));
            if (attraction != null ) {
                attraction.emergencyStop();
                Attraction.fix();
            }
            System.out.println("=============================================");
        }


//        Attraction cometExpress = new Attraction(){
//
//            @Override
//            public void startAttraction() {
//                System.out.println("혜성 특급을 시작합니다.");
//
//                long runningTime = ((long) (Math.random() * 7) * 1000) + Attraction.MIN_RUNNING_TIME_SEC;
//                if (runningTime > Attraction.MAX_RUNNING_TIME_SEC) {
//                    runningTime = Attraction.MAX_RUNNING_TIME_SEC;
//                }
//
//                try {
//                    System.out.printf("혜성 특급이 %.3f 초간 운행됩니다.\n", runningTime / 1000.0);
//                    this.run();
//                    Thread.sleep(runningTime);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                this.endAttraction();
//            }
//
//            @Override
//            public void endAttraction() {
//                System.out.println("혜성 특급이 종료되었습니다.");
//            }
//
//            @Override
//            public void run() {
//                System.out.println("혜성 특급 신나게 달리는 중!!!!\n돌아 돌아~~~붐바스틱~~@@@");
//            }
//
//            @Override
//            public void emergencyStop() {
//                System.out.println("혜성 특급은 비상 정지를 할 수 없습니다. 운행이 종료된 후 비상정지를 시도합니다.");
//            }
//        };
//
//        cometExpress.startAttraction();
//        System.out.println("=============================================");
//        cometExpress.emergencyStop();
    }
}
