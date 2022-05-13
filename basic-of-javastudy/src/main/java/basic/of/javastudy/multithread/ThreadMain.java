package basic.of.javastudy.multithread;

import java.time.LocalDateTime;

public class ThreadMain {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println("안녕하세요!! " + LocalDateTime.now());
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        // 익명 객체를 통한 Runnable 구현으로 스레드 생성
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println("안녕하세요!! " + LocalDateTime.now());
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        thread.start();

//        Thread thread = new PrintSalute();
//        thread.start();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("안녕하세요!! " + LocalDateTime.now());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread1.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("반갑습니다!! " + LocalDateTime.now());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
