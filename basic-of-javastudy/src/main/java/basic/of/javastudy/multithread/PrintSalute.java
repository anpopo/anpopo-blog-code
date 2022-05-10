package basic.of.javastudy.multithread;

import java.time.LocalDateTime;

public class PrintSalute extends Thread{
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
}
