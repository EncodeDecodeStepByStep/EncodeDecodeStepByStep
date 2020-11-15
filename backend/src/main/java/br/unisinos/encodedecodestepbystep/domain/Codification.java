package br.unisinos.encodedecodestepbystep.domain;

import org.apache.commons.lang3.mutable.MutableDouble;

import java.io.File;
import java.util.Map;

public class Codification {

    private static Boolean encodeCodification;
    private static String codificationName;
    private static Long numberOfCharsTotal;
    private static Long numberOfCharsReaded;
    private static String codeword;
    private static String characterCodification;
    private static MutableDouble progressPercentage;
    private static boolean stepsFinished;

    private static File file;
    private static Long numberOfCodewordsReaded;
    private static boolean mustSaveInCodeword;
    private static Map<Integer, Integer> huffmanSorted;
    private static Map<Character, String> huffmanTree;


    public static Long getNumberOfCodewordsReaded() {
        return numberOfCodewordsReaded;
    }

    public static void setNumberOfCodewordsReaded(Long numberOfCodewordsReaded) {
        Codification.numberOfCodewordsReaded = numberOfCodewordsReaded;
    }

    public static Map<Integer, Integer> getHuffmanSorted() {
        return huffmanSorted;
    }

    public static void setHuffmanSorted(Map<Integer, Integer> huffmanSorted) {
        Codification.huffmanSorted = huffmanSorted;
    }

    public static Map<Character, String> getHuffmanTree() {
        return huffmanTree;
    }

    public static void setHuffmanTree(Map<Character, String> huffmanTree) {
        Codification.huffmanTree = huffmanTree;
    }

    public static String getCodeword() {
        return codeword;
    }

    public static void setCodeword(String codeword) {
        Codification.codeword = codeword;
    }

    public static MutableDouble getProgressPercentage() {
        return progressPercentage;
    }

    public static void setProgressPercentage(MutableDouble progressPercentage) {
        Codification.progressPercentage = progressPercentage;
    }

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        Codification.file = file;
    }

    public static Long getNumberOfCharsTotal() {
        return numberOfCharsTotal;
    }

    public static void setNumberOfCharsTotal(Long numberOfCharsTotal) {
        Codification.numberOfCharsTotal = numberOfCharsTotal;
    }

    public static Long getNumberOfCharsReaded() {
        return numberOfCharsReaded;
    }

    public static void setNumberOfCharsReaded(Long numberOfCharsReaded) {
        Codification.numberOfCharsReaded = numberOfCharsReaded;
    }

    public static boolean isStepsFinished() {
        return stepsFinished;
    }

    public static void setStepsFinished(boolean stepsFinished) {
        Codification.stepsFinished = stepsFinished;
    }

    public static String getCharacterCodification() {
        return characterCodification;
    }

    public static void setCharacterCodification(String characterCodification) {
        Codification.characterCodification = characterCodification;
    }

    public static boolean isEncodeCodification() {
        return encodeCodification;
    }

    public static void setEncodeCodification(boolean encodeCodification) {
        Codification.encodeCodification = encodeCodification;
    }

    public static String getCodificationName() {
        return codificationName;
    }

    public static void setCodificationName(String codificationName) {
        Codification.codificationName = codificationName;
    }

    public static Boolean getEncodeCodification() {
        return encodeCodification;
    }

    public static void setEncodeCodification(Boolean encodeCodification) {
        Codification.encodeCodification = encodeCodification;
    }

    public static boolean isMustSaveInCodeword() {
        return mustSaveInCodeword;
    }

    public static void setMustSaveInCodeword(boolean mustSaveInCodeword) {
        Codification.mustSaveInCodeword = mustSaveInCodeword;
    }

    public static String staticToString() {
        return "Codification{"
        + "encodeCodification: " + encodeCodification
        + ", codificationName: " + codificationName
        + ", numberOfCharsTotal: " + numberOfCharsTotal
        + ", numberOfCharsReaded: " + numberOfCharsReaded
        + ", codeword: " + codeword
        + ", characterCodification: " + characterCodification
        + ", progressPercentage: " + progressPercentage
        + ", stepsFinished: " + stepsFinished
        + ", file: " + file
        + ", numberOfCodewordsReaded: " + numberOfCodewordsReaded
        + ", mustSaveInCodeword: " + mustSaveInCodeword
        + ", huffmanSorted: " + huffmanSorted
        + ", huffmanTree: " + huffmanTree
        + "}";
    }
}
