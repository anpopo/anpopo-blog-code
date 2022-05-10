package basic.of.javastudy.multithread;

import java.util.Map;

public class ThreadInfoPractice {
    public static void main(String[] args) {
        Thread thread = new PrintSalute();
        thread.setName("print salute~~");
        thread.setDaemon(true);
        thread.start();

        Thread tt = new Thread();

        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();

        for (Map.Entry<Thread, StackTraceElement[]> threadEntry : allStackTraces.entrySet()) {
            System.out.println("Name: " + threadEntry.getKey().getName() + (threadEntry.getKey().isDaemon() ? "(데몬)" : "(주)"));

            System.out.println("\t소속 그룹 : " + threadEntry.getKey().getThreadGroup().getName());
            System.out.println();
        }
    }
}
