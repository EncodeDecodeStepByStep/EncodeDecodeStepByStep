package br.unisinos.encodedecodestepbystep.domain;

import org.apache.commons.lang3.mutable.MutableDouble;

import java.io.File;

public class Codification {

    private static Long numberOfCharsTotal;
    private static Long numberOfCharsReaded;
    private static String codeword;
    private static String stepMade; // Descrição da ação(passo) q foi feita para mostra
    private static MutableDouble progressPercentage;
    private static boolean stepsFinished;

    private static File file;
    private static Long numberOfCodewordsReaded;


    public static Long getNumberOfCodewordsReaded() {
        return numberOfCodewordsReaded;
    }

    public static void setNumberOfCodewordsReaded(Long numberOfCodewordsReaded) {
        Codification.numberOfCodewordsReaded = numberOfCodewordsReaded;
    }

    public static String getCodeword() {
        return codeword;
    }

    public static void setCodeword(String codeword) {
        Codification.codeword = codeword;
    }

    public static String getStepMade() {
        return stepMade;
    }

    public static void setStepMade(String stepMade) {
        Codification.stepMade = stepMade;
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
}
