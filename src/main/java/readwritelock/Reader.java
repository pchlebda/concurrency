package readwritelock;

/**
 * Created by Piotr on 19.11.2015.
 */
public class Reader extends Thread {

    private static final long DEFAULT_TIME_SLEEP = 1000L;
    private Dictionary dictionary;
    private volatile boolean run = true;

    public Reader(Dictionary dictionary, String threadName) {
        super(threadName);
        this.dictionary = dictionary;
    }

    @Override
    public void run() {

        while (run) {
            String[] keys = dictionary.getKeys();
            for (String key : keys) {
                String value = dictionary.get(key);
            //    System.out.println(key + " : " + value);
            }
            try {
                Thread.sleep(DEFAULT_TIME_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopReader() {
        run = false;
    }

}
