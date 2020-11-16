package br.unisinos.encodedecodestepbystep.utils;

public class SystemUtils {

    public static final String OS = System.getProperty("os.name").toLowerCase();

    public static String getCodewordsRepositoryPath(){
        return System.getProperty("user.dir") + "/public/backend_jar/database/CodewordsSizesArray.repository";
    }
}
