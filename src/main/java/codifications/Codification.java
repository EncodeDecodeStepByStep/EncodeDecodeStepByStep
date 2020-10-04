package codifications;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public interface Codification {


    void encode(File file, JProgressBar jp) throws IOException;

    void decode(File file, JProgressBar jp) throws IOException;

    String getBitsIdentificacaoAlgoritmo();
}
