package br.unisinos.encodedecodestepbystep.utils;

public class MathUtils {
    public static int logBase2(int logaritmando) {
        return (int) (Math.log10(logaritmando) / Math.log10(2));
    }

    public static double logBase2ToDouble(int logaritmando) {
        return logaritmando == 1 ? 1 : (Math.log10(logaritmando) / Math.log10(2));
    }

}
