package br.unisinos.encodedecodestepbystep.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class HuffmanTree {

    private int length;
    private int allocatedNodes;
    private List huffmanNodes;

    public HuffmanTree(){
        this.length = 0;
        this.allocatedNodes = 0;
        this.huffmanNodes = new ArrayList();
    }

    public void add(HuffmanNode node){
        huffmanNodes =
    }
}
