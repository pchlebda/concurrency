package barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Piotr on 2015-04-12.
 */
public class CyclicBarrierExample {


    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("All parties arrived at barrier, lets play");
        });


        Thread t1 = new Thread(new Task(cyclicBarrier), "Thread 1");
        Thread t2 = new Thread(new Task(cyclicBarrier), "Thread 2");
        Thread t3 = new Thread(new Task(cyclicBarrier), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }


    private static class Task implements Runnable {

        private final CyclicBarrier barrier;

        private Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
