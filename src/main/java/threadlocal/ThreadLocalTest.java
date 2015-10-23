package threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Piotr on 23.10.2015.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task());
        Thread t2 = new Thread(new Task());

        t1.start();
        t2.start();
    }

    public static String threadSafeFormat(Date date){
        DateFormat formatter = PerThreadFormatter.getDateFormat();

        return formatter.format(date);
    }


    static class PerThreadFormatter{

        private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue() {
                System.out.println("Creating SimpleDateFormat for Thread: "+Thread.currentThread().getName());
                return new SimpleDateFormat("dd/MM/yyyy");

            }
        };

        public static DateFormat getDateFormat(){
            return dateFormatHolder.get();
        }
    }

   static class Task implements Runnable{
        @Override
        public void run() {
            for(int i = 0;i<2;++i){
                System.out.println("Thread: "+Thread.currentThread().getName()+" Formatted Date: "+ThreadLocalTest.threadSafeFormat(new Date()));
            }
        }
    }
}
