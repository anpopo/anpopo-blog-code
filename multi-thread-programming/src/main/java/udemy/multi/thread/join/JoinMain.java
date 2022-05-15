package udemy.multi.thread.join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinMain {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(1000000000L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);

        List<FactorialThread> threads = new ArrayList<>();

        for (Long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (FactorialThread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        for (FactorialThread thread : threads) {
            thread.join(3000);
        }


        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFInished) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }

        System.out.println("----------------------------");

        ComplexCalculation complexCalculation = new ComplexCalculation();

        BigInteger bigInteger = complexCalculation.calculateResult(new BigInteger("2"), new BigInteger("111241254125126"), new BigInteger("3"), new BigInteger("1234"));
        System.out.println(bigInteger);

    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFInished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFInished = true;
        }

        private BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }

            return tempResult;
        }

        public boolean isFinished () {
            return isFInished;
        }

        public BigInteger getResult() {
            return result;
        }
    }

    public static class ComplexCalculation {
        public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
            BigInteger result;

            PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
            PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

            thread1.start();
            thread2.start();

            try {
                thread1.join(10000);
                thread2.join(10000);
            } catch (InterruptedException e) {}

            thread1.interrupt();
            thread2.interrupt();
            return thread1.getResult().add(thread2.getResult());
        }

        private class PowerCalculatingThread extends Thread{
            private BigInteger result = BigInteger.ONE;
            private final BigInteger base;
            private final BigInteger power;

            public PowerCalculatingThread(BigInteger base, BigInteger power) {
                this.base = base;
                this.power = power;
            }

            @Override
            public void run() {
                for(BigInteger i = BigInteger.ZERO;
                    i.compareTo(power) !=0;
                    i = i.add(BigInteger.ONE)) {

                    if (Thread.currentThread().isInterrupted()) {
                        result =  BigInteger.ONE;
                        break;
                    }
                    result = result.multiply(base);
                }
            }

            public BigInteger getResult() {
                return this.result;
            }
        }
    }
}
