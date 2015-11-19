package readwritelock;

import java.io.IOException;

/**
 * Created by Piotr on 19.11.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = Dictionary.of("path to file");
        System.out.println("Dictionary init");
        Writer writer1 = new Writer(dictionary, "Mr. Writer 1");
        Writer writer2 = new Writer(dictionary, "Mr. Writer 2");
        Reader reader1 = new Reader(dictionary, "Mrs Reader 1");
        Reader reader2 = new Reader(dictionary, "Mrs Reader 2");
        Reader reader3 = new Reader(dictionary, "Mrs Reader 3");
        Reader reader4 = new Reader(dictionary, "Mrs Reader 4");
        Reader reader5 = new Reader(dictionary, "Mrs Reader 5");
        writer1.start();
        writer2.start();
        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();
        reader5.start();

    }
}
