package readwritelock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

/**
 * Created by Piotr on 19.11.2015.
 */
public class Dictionary {

    private static final long DEFAULT_TIME_ELAPSED = 20L;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private Map<String, String> dictionary = new HashMap<>();

    public static Dictionary of(String filePath) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(filePath));
        Dictionary dictionary = new Dictionary();
        lines.forEach(s -> dictionary.dictionary.put(s, s));
        return dictionary;
    }


    public void set(String key, String value) {
        long timeBefore = System.currentTimeMillis();
        writeLock.lock();
        long timeAfter = System.currentTimeMillis();
        long difference = timeAfter - timeBefore;
        if(difference> DEFAULT_TIME_ELAPSED)
        System.out.printf("Time elapsed for writer[%s] %d[ms]\n", Thread.currentThread().getName(), difference);
        try {
            dictionary.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key) {
        long timeBefore = System.currentTimeMillis();
        readLock.lock();
        long timeAfter = System.currentTimeMillis();
        long difference = timeAfter - timeBefore;
        if(difference>DEFAULT_TIME_ELAPSED)
            System.out.printf("Time elapsed for reader[%s] %d[ms]\n", Thread.currentThread().getName(), difference);
        try {
            return dictionary.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public String[] getKeys() {
        readLock.lock();
        try {
            String[] keys = new String[dictionary.size()];
            return dictionary.keySet().toArray(keys);
        } finally {
            readLock.unlock();
        }
    }

}
