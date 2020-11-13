package br.unisinos.encodedecodestepbystep.controller.mapper;

import br.unisinos.encodedecodestepbystep.controller.response.EncodedDTO;
import br.unisinos.encodedecodestepbystep.domain.Codification;

public class EncodedDTOMapper {

    public static EncodedDTO getEncodedDTO() {
        return EncodedDTO.builder()
                .codificationName(Codification.getCodificationName())
                .encodeCodification(Codification.getEncodeCodification())
                .codeword(Codification.getCodeword())
                .numberOfCharsTotal(Codification.getNumberOfCharsTotal())
                .numberOfCharsReaded(Codification.getNumberOfCharsReaded())
                .characterBeforeEncode(Codification.getCharacterCodification())
                .progressPercentage(Codification.getProgressPercentage().getValue())
                .stepsFinished(Codification.isStepsFinished())
                .build();
    }

    public static EncodedDTO getHuffmanEncodedDTO() {
        EncodedDTO encodedDTO = getEncodedDTO();
        encodedDTO.setHuffmanCount(Codification.getHuffmanSorted());
        encodedDTO.setHuffmanTree(Codification.getHuffmanTree());
        return encodedDTO;
    }


}

