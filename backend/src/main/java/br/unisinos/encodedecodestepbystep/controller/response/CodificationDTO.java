package br.unisinos.encodedecodestepbystep.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodificationDTO {

    private Boolean encodeCodification;
    private String codificationName;
    private Long numberOfCharsTotal;
    private Long numberOfCharsReaded;
    private String stepMade; // Descrição da ação(passo) q foi feita para mostra
    private Double progressPercentage;
    private Boolean stepsFinished;

    //Encode
    private String characterBeforeEncode;
    private String codeword;

    //Decode
    private String bitsBeforeDecode;
    private String characterDecoded;

    //Huffman
    private Map<Character, String> huffmanTree;
    private Map<Integer, Integer> huffmanCount;
}
