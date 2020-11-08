package br.unisinos.encodedecodestepbystep.controller;

import br.unisinos.encodedecodestepbystep.controller.mapper.DecodedDTOMapper;
import br.unisinos.encodedecodestepbystep.controller.mapper.EncodedDTOMapper;
import br.unisinos.encodedecodestepbystep.controller.response.CodificationDTO;
import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.domain.ReaderWriterWrapper;
import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.service.codification.HammingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequestMapping("/hamming")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HammingController {

    private final HammingService hammingService;

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
                hammingService.encode(readerWriterWrapper.getWriterInterface(), readerWriterWrapper.getReaderInterface());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/nextStep")
    @ResponseStatus(HttpStatus.OK)
    public CodificationDTO nextStep() throws IOException {
        Codification.setStepMade("Não faço ideia, pois não codei o algoritmo");
        Codification.setCodeword(new Reader().readNextStep());
        return Codification.isEncodeCodification() ? EncodedDTOMapper.getEncodedDTO() : DecodedDTOMapper.getDecodedDTO();
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/normal/decode")
    @ResponseStatus(HttpStatus.OK)
    public void decode(@RequestBody String path) {
        Codification.setStepsFinished(false);
        Codification.setEncodeCodification(false);
        new Thread(() -> {
            try {
                Codification.setProgressPercentage(new MutableDouble(0));
                ReaderWriterWrapper readerWriterWrapper = ReaderWriterWrapper.getDecodeReaderWriterWrapperNormal(path, Codification.getProgressPercentage());
                hammingService.decode(readerWriterWrapper.getWriterInterface(), readerWriterWrapper.getReaderInterface());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
