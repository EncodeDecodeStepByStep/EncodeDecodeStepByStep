package br.unisinos.encodedecodestepbystep.controller;

import br.unisinos.encodedecodestepbystep.controller.mapper.EncodedDTOMapper;
import br.unisinos.encodedecodestepbystep.controller.response.CodificationDTO;
import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.domain.ReaderWriterWrapper;
import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.service.codification.HuffmanNodeService;
//import br.unisinos.encodedecodestepbystep.service.codification.HuffmanService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequestMapping("/huffman")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HuffmanController {

    private final HuffmanNodeService huffmanService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/normal/encode")
    @ResponseStatus(HttpStatus.OK)
    public void encode(@RequestBody String path) {
        Codification.setStepsFinished(false);
        Codification.setEncodeCodification(true);
        new Thread(() -> {
            try {
                Codification.setProgressPercentage(new MutableDouble(0));
                ReaderWriterWrapper readerWriterWrapper = ReaderWriterWrapper.getEncodeReaderWriterWrapperNormal(path, Codification.getProgressPercentage());
                huffmanService.encode(readerWriterWrapper.getWriterInterface(), readerWriterWrapper.getReaderInterface());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/hashes")
    @ResponseStatus(HttpStatus.OK)
    public CodificationDTO hashes() throws IOException {
        return EncodedDTOMapper.getHuffmanEncodedDTO();
    }
}
