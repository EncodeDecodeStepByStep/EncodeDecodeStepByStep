package br.unisinos.encodedecodestepbystep.service.codification;


import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;

import java.io.IOException;

public interface CodificationService {


    void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection;

    void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection;

    String getBitsIdentificacaoAlgoritmo(WriterInterface writer);
}
