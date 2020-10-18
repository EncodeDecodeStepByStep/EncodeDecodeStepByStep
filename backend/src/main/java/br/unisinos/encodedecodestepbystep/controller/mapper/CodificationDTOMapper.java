package br.unisinos.encodedecodestepbystep.controller.mapper;

import br.unisinos.encodedecodestepbystep.controller.response.CodificationDTO;
import br.unisinos.encodedecodestepbystep.domain.Codification;

public class CodificationDTOMapper {

    public static CodificationDTO getCodificationDTO() {
        return CodificationDTO.builder()
                .codeword(Codification.getCodeword())
                .numberOfCharsTotal(Codification.getNumberOfCharsTotal())
                .numberOfCharsReaded(Codification.getNumberOfCharsReaded())
                .characterBeforeCodification(Codification.getCharacterBeforeCodification())
                .progressPercentage(Codification.getProgressPercentage().getValue())
                .stepMade(Codification.getStepMade())
                .stepsFinished(Codification.isStepsFinished())
                .build();
    }
}

