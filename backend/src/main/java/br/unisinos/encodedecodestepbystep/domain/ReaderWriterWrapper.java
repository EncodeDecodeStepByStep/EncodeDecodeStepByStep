package br.unisinos.encodedecodestepbystep.domain;

import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReaderWriterWrapper {

    private ReaderInterface readerInterface;
    private WriterInterface writerInterface;
}
