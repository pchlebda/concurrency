package starvation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Piotr on 09.05.2016.
 */
public class ThreadStarvation {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        System.out.println("Start program..");
        ThreadStarvation threadStarvation = new ThreadStarvation();
        threadStarvation.executorService.submit(threadStarvation.new Task());

    }

    class Task implements Callable<String> {
        public String call() throws Exception {
            Future<?> future = executorService.submit(() -> System.out.println("Never executed"));
            System.out.println("Waiting..");
            future.get();
            return "Task!";
        }
    }
}
