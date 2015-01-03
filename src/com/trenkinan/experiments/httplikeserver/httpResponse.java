package com.trenkinan.experiments.httplikeserver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Created by trenkinan on 30.10.14.
 */
public class httpResponse {
    private String responseCode;
    private byte[] data;
    private InputStream inputStream;
    private final static String server = "trenkinan java server 0.1";
    private final static  String protocol = "HTTP/1.1";
    public httpResponse(String responseCode, byte[] data) {
        this.responseCode = responseCode;
        this.data = data;
        if(responseCode.equals("404")) {
            this.data = new String("Sorry, this page was not found\n").getBytes();
        }
    }
    public httpResponse(String responseCode, InputStream inputStream) {
        this.responseCode = responseCode;
        this.inputStream = inputStream;
        if(this.responseCode.equals("404")) {
            this.inputStream = new ByteArrayInputStream("Sorry, this page was not found\n".getBytes(StandardCharsets.UTF_8));
        }
    }
    public void setInputStream(InputStream inputStream){
        this.inputStream = inputStream;
    }
    public byte[] getHeaderBytes(){
        int contentLength = 0;
        if(data!=null)
            contentLength = data.length;
        byte[] bytes = new String(protocol + " " + responseCode + "\n" +
                "Date: " + new Date().toString() + "\n" +
                "com.trenkinan.experiments.httplikeserver.Server: " + server + "\n" +
                "Content-Length: " + contentLength).getBytes(StandardCharsets.UTF_8);
        return bytes;
    }

    public InputStream getContentInputStream() {
        return this.inputStream;
    }
    @Deprecated
    public byte[] getContentBytes() {
        return data;
    }
    @Deprecated
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
