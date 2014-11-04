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
    private final String serverRoot;
    public HttpHelper(Socket socket, String serverRoot) {
        this.socket = socket;
        System.out.println("HttpHelper.java: constructor invoked");
        this.serverRoot = serverRoot;
    }

    @Override
    public void run() {
        System.out.println("HttpHelper.java: thread spawned");
        httpRequest request = getRequest();
        if (request == null)
            return;
        httpFileHelper fileHelper = new httpFileHelper(request.getRequestedFileUri());
        System.out.println("requested file uri: " + request.getRequestedFileUri());
        sendResponse(fileHelper.buildResponse());
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Can not close socket: " + e.getCause());
        }
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println("Can not close I/O streams");
        }
    }

    /**
     * Call it to process Data from socket(which is a request) and get the Request object
     * @return Request which has been build from data,received by socket
     */
    private httpRequest getRequest() {
        System.out.println("getRequest invoked");
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
               // System.out.println(ln + ": " + ln.length());
               // System.out.flush();
                requestStrings.add(ln);
                //break;
            }
        } catch (IOException e) {
            System.out.println("Error while reading the message from inputStream");
            System.exit(-1);
        }
        if(requestStrings.size() == 0)
            return null;
        return new httpRequest(requestStrings, this.serverRoot);
    }
    private void sendResponse(httpResponse response) {
        ;//socket stuff
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        try {
            //writer.write(new String(response.getBytes()));
            out.write(response.getBytes());
            out.flush();
            //System.out.println("wrting to socket: " + new String(response.getBytes()));
            //writer.flush();
        } catch (IOException ioe) {
            System.out.println("httpHelper, Can not write to Writer: " + ioe.getCause());
        }
    }
}
