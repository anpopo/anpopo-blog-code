package udemy.multi.thread.atomic;

import javax.swing.plaf.metal.MetalIconFactory;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogic1 = new BusinessLogic(metrics);
        BusinessLogic businessLogic2 = new BusinessLogic(metrics);

        MetricPrinter metricPrinter = new MetricPrinter(metrics);

        businessLogic1.start();
        businessLogic2.start();
        metricPrinter.start();
    }

    public static class MetricPrinter extends Thread {
        private Metrics metrics;
        public MetricPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {

            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // getAverage 는 synchronized 메소드가 아니기 때문에 다른 스레드의 실행해 영향을 끼치지 않는다.
                double currentAverage = metrics.getAverage();
                System.out.println("Current Average is " + currentAverage);

            }
        }
    }

    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {

            while (true) {
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long end = System.currentTimeMillis();

                metrics.addSample(end - start);
            }
        }
    }

    public static class Metrics {
        private volatile long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
