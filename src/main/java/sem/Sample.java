package sem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Piotr on 2015-04-15.
 */
public class Sample {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Thread thread = new Thread(new Task(lock), "Thread 2");
        thread.start();
        System.out.println(Thread.currentThread().getName() + " tries lock");
        lock.lock();
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " has just aquired lock");
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    private static class Task implements Runnable {

        private final Lock lock;

        private Task(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + " tries lock");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " has just aquired lock");

            } finally {
                lock.unlock();
            }

        }
    }
}
