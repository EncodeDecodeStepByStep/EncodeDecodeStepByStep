package br.unisinos.encodedecodestepbystep.controller;

import br.unisinos.encodedecodestepbystep.controller.mapper.DecodedDTOMapper;
import br.unisinos.encodedecodestepbystep.controller.mapper.EncodedDTOMapper;
import br.unisinos.encodedecodestepbystep.controller.request.EncodeRequest;
import br.unisinos.encodedecodestepbystep.controller.response.CodificationDTO;
import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.domain.ReaderWriterWrapper;
import br.unisinos.encodedecodestepbystep.repository.codification.Reader;
import br.unisinos.encodedecodestepbystep.service.codification.GoulombService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequestMapping("/goulomb")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoulombController {

    private final GoulombService goulombService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/normal/encode")
    @ResponseStatus(HttpStatus.OK)
    public void encode(@RequestBody EncodeRequest encodeRequest) {
        goulombService.setDivisor(encodeRequest.getDivisor());
        Codification.setStepsFinished(false);
        Codification.setEncodeCodification(true);
        new Thread(() -> {
            try {
                Codification.setProgressPercentage(new MutableDouble(0));

                ReaderWriterWrapper readerWriterWrapper = ReaderWriterWrapper.getEncodeReaderWriterWrapperNormal(encodeRequest.getPath(), Codification.getProgressPercentage());
                goulombService.encode(readerWriterWrapper.getWriterInterface(), readerWriterWrapper.getReaderInterface());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
