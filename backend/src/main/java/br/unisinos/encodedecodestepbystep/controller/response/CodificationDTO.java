package br.unisinos.encodedecodestepbystep.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CodificationDTO {

    private Long numberOfCharsTotal;
    private Long numberOfCharsReaded;
    private String characterBeforeCodification;
    private String codeword;
    private String stepMade; // Descrição da ação(passo) q foi feita para mostra
    private Double progressPercentage;
    private Boolean stepsFinished;
}
