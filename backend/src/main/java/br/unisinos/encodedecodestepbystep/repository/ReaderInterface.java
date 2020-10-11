package br.unisinos.encodedecodestepbystep.repository;

import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;

import java.io.IOException;

public interface ReaderInterface {

    int read() throws IOException;

    int readNextChar() throws IOException, WrongFormatExpection;

    int readNextCharSemHamming() throws IOException;

    int readNextCharWithHamming() throws IOException, WrongFormatExpection;

    void close() throws IOException;

    String readCabecalho() throws IOException, WrongFormatExpection;
}
