package udemy.multi.thread.atomic;

public class MinMaxMetrics {

    private volatile long minValue;
    private volatile long maxValue;


    public MinMaxMetrics () {
        this.maxValue = Long.MIN_VALUE;
        this.minValue = Long.MAX_VALUE;
    }

    /**
     * 원자적이고 경쟁 상태인 메소드 - 다른 스레드의 실행에 영향을 미친다.
     */
    public void addSample(long newSample) {
        synchronized (this) {
            this.minValue = Math.min(minValue, newSample);
            this.maxValue = Math.max(maxValue, newSample);
        }
    }

    /**
     * 원자적이고 경쟁 상태가 아닌 메소드
     */
    public long getMinValue() {
        return minValue;
    }

    /**
     * 원자적이고 경쟁 상태가 아닌 메소드
     */
    public long getMaxValue() {
        return maxValue;
    }
}
