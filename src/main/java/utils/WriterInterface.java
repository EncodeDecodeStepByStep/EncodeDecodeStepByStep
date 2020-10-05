package utils;

import expections.WrongFormatExpection;

import java.io.IOException;

public interface WriterInterface {

    void write(char letter) throws IOException;

    void write(String bits) throws IOException, WrongFormatExpection;

    void writeSemHamming(String bits) throws IOException, WrongFormatExpection;

    void close() throws IOException;
}
