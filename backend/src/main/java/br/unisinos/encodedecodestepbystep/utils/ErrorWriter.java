package br.unisinos.encodedecodestepbystep.utils;

import java.io.*;

public class ErrorWriter {
    private static ErrorWriter instance;
    private String path;
    private int counter;

    private ErrorWriter(String path){
        this.path = path;
        this.counter = 1;
    }

    private ErrorWriter(){}

    public static void setFile(String path, String fileName)  {
            String finalPath = path + "\\"+"error-"+fileName;
            instance = new ErrorWriter(finalPath);
    }

    public static ErrorWriter getInstance() {
        return instance==null? new ErrorWriter(): instance;
    }

    public void write(String log){
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            if(file.length()==0){
                writer.write("Log de erros da codificação\n");
            }
            writer.append("\n"+(counter++)+". "+log);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
