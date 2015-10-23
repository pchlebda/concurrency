package lock;

/**
 * Created by Piotr on 2015-04-12.
 */
public class Main {

    public static void main(String[] args) {

        BoundedBuffer boundedBuffer = new BoundedBuffer();
        int PROD_N = 3;
        int CONS_N = 1;
        Thread[] producers = new Thread[PROD_N];
        Thread[] consumers = new Thread[CONS_N];

        for (int i = 0; i < PROD_N; ++i) {
            producers[i] = new Thread(new Producer(boundedBuffer),"Producer - "+i);
            producers[i].start();
        }

        for (int i = 0; i < CONS_N; ++i) {
            consumers[i] = new Thread(new Consumer(boundedBuffer),"Consumer - "+i);
            consumers[i].start();
        }


    }


    private static class Producer implements Runnable {

        private final BoundedBuffer boundedBuffer;

        private Producer(BoundedBuffer boundedBuffer) {
            this.boundedBuffer = boundedBuffer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Object obj = new String(Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + " put " + obj);
                    boundedBuffer.put(obj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        private final BoundedBuffer boundedBuffer;

        private Consumer(BoundedBuffer boundedBuffer) {
            this.boundedBuffer = boundedBuffer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                    Object obj = boundedBuffer.take();
                    System.out.println(Thread.currentThread().getName() + " took " + obj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
