import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by trenkinan on 27.10.14.
 */
public class FileHelper {
    private final File file;
    private final BufferedReader bufferedReader;
    public FileHelper(String fileName) throws IOException {
        file = new File(fileName);
        if(!file.exists() || file.isDirectory()) {
            throw new IOException("File does not exists or it is directory: " + fileName);
        }
        if(!file.canRead()) {
            throw new IOException("Can not read file: " + fileName);
        }
        bufferedReader = new BufferedReader(new FileReader(file));

    }
}
