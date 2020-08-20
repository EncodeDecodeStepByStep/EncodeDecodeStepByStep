package utils;

import java.io.*;

public class Writer {
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    public Writer(String path) throws IOException {
        File output = new File(path);

        if(output.exists()){
            output.delete();
        }
        this.fileWriter = new FileWriter(output);
        this.bufferedWriter = new BufferedWriter(fileWriter);
    }

    public void write(String letter) throws IOException {
        bufferedWriter.write(letter);
    }

    public void write(char letter) throws IOException {
        bufferedWriter.write(letter);
    }

    public void close() throws IOException {
        bufferedWriter.close();
        fileWriter.close();
    }
}
