package br.unisinos.encodedecodestepbystep.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncodeRequest {
    private String path;
    private int divisor;

    public EncodeRequest(String path) {
        this.path = path;
    }

    public EncodeRequest(String path, int divisor) {
        this.path = path;
        this.divisor = divisor;
    }
}
