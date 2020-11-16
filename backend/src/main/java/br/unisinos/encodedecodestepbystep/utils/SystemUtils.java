package br.unisinos.encodedecodestepbystep.utils;

public class SystemUtils {

    public static final String OS = System.getProperty("os.name").toLowerCase();

    public static String getCodewordsRepositoryPath(){
        if(OS.contains("nix") ||OS.contains("nux") ||OS.contains("aix")) {
            return System.getProperty("user.dir") + "/linux-unpacked/public/backend_jar/database/CodewordsSizesArray.repository";
        } else {
            return System.getProperty("user.dir") + "/public/backend_jar/database/CodewordsSizesArray.repository";
        }
    }
}
