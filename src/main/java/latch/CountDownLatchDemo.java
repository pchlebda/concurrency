package latch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Piotr on 2015-04-04.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread cacheService = new Thread(new Service("Cache service", 3000, countDownLatch));
        Thread alertService = new Thread(new Service("Alert service", 5000, countDownLatch));
        Thread validationService = new Thread(new Service("Validation service", 4000, countDownLatch));

        cacheService.start();
        alertService.start();
        validationService.start();

        try {
            countDownLatch.await();
            System.out.println("All services are up, Application is starting now");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class Service implements Runnable {

        private final String name;
        private final int timeToStart;
        private final CountDownLatch latch;

        Service(String name, int timeToStart, CountDownLatch latch) {
            this.name = name;
            this.timeToStart = timeToStart;
            this.latch = latch;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(timeToStart);

            } catch (InterruptedException e) {
            }

            System.out.println(name + "is Up");
            latch.countDown();

        }
    }

}
