package readwritelock;

import java.util.Random;

/**
 * Created by Piotr on 19.11.2015.
 */
public class Writer extends Thread {

    private static final long DEFAULT_SLEEP_TIME = 1000L;
    private volatile boolean run = true;
    private final Random random = new Random();
    private Dictionary dictionary;

    public Writer(Dictionary dictionary, String threadName) {
        super(threadName);
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        while (run) {

            String[] keys = dictionary.getKeys();
            for (String key : keys) {
                String newValue = getNewValue(key);
                dictionary.set(key, newValue);
            }
            try {
                Thread.sleep(DEFAULT_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopWriter() {
        run = false;
    }

    private String getNewValue(String key) {
        return key + "value_" + random.nextInt();
    }

}
