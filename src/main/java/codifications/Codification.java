package codifications;

import java.io.File;
import java.io.IOException;

public interface Codification {


    void encode(File file) throws IOException;
    void decode(File file) throws IOException;
}
