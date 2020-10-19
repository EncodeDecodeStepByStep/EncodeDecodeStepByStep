package br.unisinos.encodedecodestepbystep.controller.mapper;

import br.unisinos.encodedecodestepbystep.controller.response.DecodedDTO;
import br.unisinos.encodedecodestepbystep.domain.Codification;

public class DecodedDTOMapper {

    public static DecodedDTO getDecodedDTO() {
        return DecodedDTO.builder()
                .codificationName(Codification.getCodificationName())
                .encodeCodification(Codification.getEncodeCodification())
                .characterDecoded(Codification.getCharacterCodification())
                .numberOfCharsTotal(Codification.getNumberOfCharsTotal())
                .numberOfCharsReaded(Codification.getNumberOfCharsReaded())
                .bitsBeforeDecode(Codification.getCodeword())
                .progressPercentage(Codification.getProgressPercentage().getValue())
                .stepMade(Codification.getStepMade())
                .stepsFinished(Codification.isStepsFinished())
                .build();
    }
}

