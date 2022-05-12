package udemy.multi.thread;

public class MultiThreadMain {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are now in thread" + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());

                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        // 스레드 내부에서 발생한 예외가 어디에서도 잡히지 않으면 UncaughtExceptionHandler 가 호출되 예외를 처리해준다.

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // 리소스 정리 혹든 추가 데이터 로깅 등의 작업을 실질적으로 한다.
                System.out.println("A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());
            }
        });

        System.out.println("We are in thread : " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();  // 운영 체제에 의해 비동기적으로 스레드 스케줄링 되는 시간이 필요하기 때문에 시간이 걸린다.
        System.out.println("We are in thread : " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(10000);


    }
}
