package utils;


import java.io.*;

public class ErrorWriter {
    private static ErrorWriter instance;
    private BufferedWriter writer;
    private File file;
    private int counter;

    private ErrorWriter(BufferedWriter writer, File file){
        this.writer = writer;
        this.file = file;
        this.counter = 1;
    }

    private ErrorWriter(){}

    public static void setFile(String path, String fileName)  {
        try{
            String finalPath = path + "\\"+"error-"+fileName;
            File file = new File(finalPath);
            if(file.exists()){
                file.delete();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            instance = new ErrorWriter(writer, file);
        }catch (Exception e){}
    }

    public static ErrorWriter getInstance() {
        return instance==null? new ErrorWriter(): instance;
    }

    public void write(String log){

            try {
                if(instance!=null){
                    if(file.length()==0){
                        writer.write("Log de erros da codificação\n");
                    }
                    writer.write("\n"+(counter++)+". "+log);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void close(){
        try {
            if(instance!=null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
