package basic.of.javastudy.multithread;

public class DataBox {
    private String data;

    public synchronized String getData() {
        if (this.data == null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        String ret = data;
        System.out.println("ConsumerThread 가 읽은 데이터 : " + ret);

        data = null;
        notify();
        return ret;
    }

    public synchronized void setData(String data) {
        if (this.data != null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        this.data = data;
        System.out.println("ProducerThread 가 생성한 데이터 : " + data);
        notify();
    }
}
