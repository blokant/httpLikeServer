import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by trenkinan on 30.10.14.
 */
public class httpResponse {
    private String responseCode;
    private byte[] data;
    private OutputStream outputStream;
    private final static String server = "trenkinan java server 0.1";
    private final static  String protocol = "HTTP/1.1";
    public httpResponse(String responseCode, byte[] data) {
        this.responseCode = responseCode;
        this.data = data;
        if(responseCode.equals("404")) {
            this.data = new String("Sorry, this page was not found\n").getBytes();
        }
    }
    public httpResponse(String responseCode, OutputStream outputStream) {
        this.responseCode = responseCode;
        this.outputStream = outputStream;
        if(this.responseCode.equals("404")) {
            this.outputStream = new ByteArrayOutputStream(new Byte("Sorry, this page was not found\n"));
        }
    }
    public byte[] getHeaderBytes(){
        int contentLength = 0;
        if(data!=null)
            contentLength = data.length;
        byte[] bytes = new String(protocol + " " + responseCode + "\n" +
                "Date: " + new Date().toString() + "\n" +
                "Server: " + server + "\n" +
                "Content-Length: " + contentLength).getBytes();
        return bytes;
    }
    /*
    public OutputStream getContentOutputStream() {

    }*/
    public byte[] getContentBytes() {
        return data;
    }
    public byte[] getBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        try {
            outputStream.write( getHeaderBytes() );
            outputStream.write( new String("\n\n").getBytes() );
            if(null != data)
                outputStream.write( getContentBytes() );
        } catch (IOException ioe) {
            System.out.println("Can not combine two byte arrays: " + ioe.getCause().toString());
        }
        return outputStream.toByteArray();
    }
}
