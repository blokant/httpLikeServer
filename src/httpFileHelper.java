import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by trenkinan on 27.10.14.
 */
public class httpFileHelper {
    private final File file;
    private final BufferedReader bufferedReader;
    private final boolean fileReadable;
    public httpFileHelper(String fileName) throws IOException {
        file = new File(fileName);
       /*
        if(!file.exists() || file.isDirectory()) {
            throw new IOException("File does not exists or it is a directory: " + fileName);
        }
        if(!file.canRead()) {
            throw new IOException("Can not read the file: " + fileName);
        }
        */
        if(!file.isDirectory() || !file.exists() || !file.canRead()) {
            fileReadable = false;
        } else {
            fileReadable = true;
        }


        bufferedReader = new BufferedReader(new FileReader(file));
    }
    public httpResponse buildResponse() {
        if(!fileReadable) {
            return new httpResponse("404",0,null);
        }
        return null;
    }

}