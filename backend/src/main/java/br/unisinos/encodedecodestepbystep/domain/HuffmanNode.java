package br.unisinos.encodedecodestepbystep.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HuffmanNode {
    private String codeword;
    private int id;
    private char character;
    private int count;
    private HuffmanNode father;
    private HuffmanNode right;
    private HuffmanNode left;
    private boolean alreadyAllocated;
    private boolean isOnTop;

    public HuffmanNode(int id, char character, int count){
        this.id = id;
        this.character = character;
        this.count = count;
        this.alreadyAllocated = false;
        this.isOnTop = true;
    }

}
