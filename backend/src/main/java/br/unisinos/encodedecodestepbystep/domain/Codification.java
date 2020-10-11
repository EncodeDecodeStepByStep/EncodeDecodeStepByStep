package br.unisinos.encodedecodestepbystep.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Codification {

    private Long id;
    private String codeword;
    private String stepMade; // Descrição da ação(passo) q foi feita para mostra

}
