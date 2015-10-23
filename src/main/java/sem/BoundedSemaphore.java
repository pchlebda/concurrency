package sem;

/**
 * Created by Piotr on 2015-04-15.
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Antek
 */
public class BoundedSemaphore {
    private final int K;
    private int N;
    private final Lock delay=new ReentrantLock(true);
    private final Lock mutex=new ReentrantLock(true);


    public BoundedSemaphore(int K, int N){
        this.K=K;
        this.N=N;
        delay.lock();
    }




    public void P(){
        mutex.lock();
        if(mutex.tryLock())
            System.out.println("nie powinno się wyświetlić");
        /*--N;
        if(N<0){
            mutex.unlock();
            System.out.println("czekam");
            delay.lock();
            System.out.println("aaa");
            //mutex.unlock();

        }
        else{
            System.out.println("mutex unlock");
            mutex.unlock();
        }*/
        System.out.println("aaa");
    }

    public void V(){
        mutex.lock();
        /*
        if(N<K){
            ++N;
            if(N<=0){
                System.out.println("zwolnienie blokady");
                delay.unlock();
            }
            else{
            System.out.println("mutex unlock");
                mutex.unlock();
            }
        }
        */
        System.out.println("bbb");
    }
}