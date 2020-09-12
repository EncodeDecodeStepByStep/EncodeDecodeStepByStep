package utils;

import java.io.*;

public class Reader {
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private InputStream is;

    public Reader(File file) throws FileNotFoundException {
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
        this.is = new FileInputStream(file);
    }

    public int readString() throws IOException {
        return bufferedReader.read();
    }

    public byte[] readByte() throws IOException {
        return is.readAllBytes();
    }

    public void close() throws IOException {
        fileReader.close();
        bufferedReader.close();
        is.close();
    }
}
