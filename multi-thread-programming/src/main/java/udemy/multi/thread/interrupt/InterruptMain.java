package udemy.multi.thread.interrupt;

import java.math.BigInteger;

public class InterruptMain {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new BlockingTask());

        thread1.start();
        thread1.interrupt();  // -> 스레드가 실행 대기 상태가 되는 순간 InterruptedException 발생

        Thread thread2 = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("10000000000")));
        thread2.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}
        System.out.println("after 10 seconds waiting thread2 interrupt");
        thread2.interrupt();

    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500000);  // -> 스레드가 실행 대기 상태가 됨.
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

    private static class LongComputationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow (BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }

                result = result.multiply(base);
            }

            return result;
        }
    }
}
