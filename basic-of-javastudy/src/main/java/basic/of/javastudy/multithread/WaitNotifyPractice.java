package basic.of.javastudy.multithread;

import javax.xml.crypto.Data;

public class WaitNotifyPractice {
    public static void main(String[] args) {
        DataBox dataBox = new DataBox();

        ProducerThread p = new ProducerThread(dataBox);
        ConsumerThread c = new ConsumerThread(dataBox);

        p.start();
        c.start();
    }
}
