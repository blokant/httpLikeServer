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
    private  BufferedReader bufferedReader;
    private  boolean fileReadable;
    public httpFileHelper(String fileName) {
        if(fileName == null) {
            file = null;
            fileReadable = false;
            return;
        }
       // System.out.println("httpFileHelper.java, fileName: " + fileName);
        if(fileName.equals("/"))
            file = new File("index.html");
        else
            file = new File(fileName.substring(1));
       /*
        if(!file.exists() || file.isDirectory()) {
            throw new IOException("File does not exists or it is a directory: " + fileName);
        }
        if(!file.canRead()) {
            throw new IOException("Can not read the file: " + fileName);
        }
        */
        if(!file.isFile()) {
            fileReadable = false;
            return;
        }
      //  System.out.println("FileName: " + file.getName());

        fileReadable = true;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (IOException ioe) {
            System.out.println("httpFileHelper.java, Can not open file: " + fileName + ioe.getCause().toString());
            bufferedReader = null;
        }

    }
    public httpResponse buildResponse() {
        System.out.println("buildResponse()");
        if(!fileReadable) {
            return new httpResponse("404",null);
        }
        //return content
        byte[] data = null;
       // System.out.println("going to read all data from: " + file.toPath());
        try {
            data = Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            System.out.println("Can not read bytes from file: " + file.getAbsolutePath() + " cause: " + e.getCause());
            return null;
        }
       // System.out.println("buildResponse(), data: " + new String(data));
        return new httpResponse("200 OK", data);
    }
}
