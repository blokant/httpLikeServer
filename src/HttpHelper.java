import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Created by trenkinan on 27.10.14.
 */
public class HttpHelper implements Runnable {
    private final Socket socket;
    private InputStream in = null;
    private OutputStream out = null;
    public HttpHelper(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        getRequest();
    }

    /**
     * Call it to process Data from socket(which is a request) and get the Request object
     * @return Request which has been build from data,received by socket
     */
    private httpRequest getRequest() {

        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            System.out.println("Can not get input/output streams");
            System.exit(-1);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String ln = null;
        java.util.List<String> requestStrings = new ArrayList();
        try {
            System.out.println("Request from the " + socket.getInetAddress().toString());
            while ((ln = reader.readLine()) != null && ln.length() != 0) {
                System.out.println(ln + ": " + ln.length());
                System.out.flush();
                requestStrings.add(ln);
                //break;
            }
        } catch (IOException e) {
            System.out.println("Error while reading the message from inputStream");
            System.exit(-1);
        }

        return new httpRequest(requestStrings);
    }
    private void sendResponse(String response) {
        ;//socket stuff
    }
}
