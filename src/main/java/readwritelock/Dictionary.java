package readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Piotr on 19.11.2015.
 */
public class Dictionary {

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private Map<String, String> dictionary = new HashMap<>();


    public void set(String key, String value) {
        writeLock.lock();
        try {
            dictionary.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key) {
        readLock.lock();
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
