package br.unisinos.encodedecodestepbystep.repository;

import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;

import java.io.File;
import java.io.IOException;

public interface WriterInterface {

    void write(char letter, String bitsReaded) throws IOException;

    void write(String bits) throws IOException, WrongFormatExpection;

    void writeSemHamming(String bits) throws IOException, WrongFormatExpection;

    void close() throws IOException;

    File getFile();
}
