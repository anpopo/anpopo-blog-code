package udemy.multi.thread.atomic;

public class DataRacePractice {
    public static void main(String[] args) {

        SharedClass sharedClass = new SharedClass();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increase();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.printError();
            }
        });


        thread1.start();
        thread2.start();

    }

    private static class SharedClass {
        private volatile int x;
        private volatile int y;

        public void increase() {
            this.x++;
            this.y++;
        }

        public void printError() {
            if (y > x) {
                System.out.println("Data Race Condition occurred");
            }
        }
    }
}
