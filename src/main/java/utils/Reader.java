package utils;

import java.io.*;

public class Reader {
    private BufferedReader bufferedReader;
    private FileReader fileReader;

    public Reader(File file) throws FileNotFoundException {
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
    }

    public int read() throws IOException {
        return bufferedReader.read();
    }

    public void close() throws IOException {
        fileReader.close();
        bufferedReader.close();
    }
}
