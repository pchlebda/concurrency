package sem;

/**
 * Created by Piotr on 2015-04-15.
 */
public class Main {


    public static final BoundedSemaphore  semaphore = new BoundedSemaphore(1, 0);
    public static void main(String[] args) {

        Thread t=new Thread(new Runnable() {

            @Override
            public void run() {
                semaphore.P();
                semaphore.P();
                semaphore.P();
                semaphore.P();
            }
        });


        Thread t1=new Thread(new Runnable() {

            @Override
            public void run() {
                semaphore.V();
            }
        });

        t.run();
    }

}