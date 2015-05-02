package com.trenkinan.experiments.httplikeserver;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by trenkinan on 27.10.14.
 */
public class httpFileHelper implements HttpRequestHandler{
    private final File file;
    private  BufferedReader bufferedReader;
    private  boolean fileReadable;
    public httpFileHelper(String fileName) {
        System.out.println("com.trenkinan.experiments.httplikeserver.httpFileHelper.java constructor invoked");
        if(fileName == null) {
            file = null;
            fileReadable = false;
            return;
        }
       // System.out.println("com.trenkinan.experiments.httplikeserver.httpFileHelper.java, fileName: " + fileName);
        if(fileName.endsWith("/"))
            file = new File(fileName + "index.htm");
        else
            file = new File(fileName);

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
        System.out.println("FileHelper: FileName: " + file.getName());

        fileReadable = true;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (IOException ioe) {
            System.out.println("com.trenkinan.experiments.httplikeserver.httpFileHelper.java, Can not open file: " + fileName + ioe.getCause().toString());
            bufferedReader = null;
        }

    }
    private httpResponse build404() {
        return new httpResponse("404", (byte[]) null);
    }
    public httpResponse buildResponse()  {
        System.out.println("buildResponse()");
        if(!fileReadable) {
            return build404();
        }
        //return content
        byte[] data = null;
       // System.out.println("going to read all data from: " + file.toPath());
        //Legacy stuff
        try {
            if (file.length() > 5 * 1024 * 1024) {
                System.out.println("Can not read file old way: it's bigger than 5MB, using new stream way");
            } else {
                data = Files.readAllBytes(file.toPath());
            }
        } catch (Exception e) {
            System.out.println("Can not read bytes from file: " + file.getAbsolutePath() + " cause: " + e.getCause());
            return build404();
        }
       // System.out.println("buildResponse(), data: " + new String(data));
        FileInputStream fis;
        try {
            System.out.println("Trying to get input from: " + file.getAbsolutePath());
            fis = new FileInputStream(file);
            if(fis == null) {
                System.out.println("fis is null even here !");
            }
        } catch (FileNotFoundException e) {
            System.out.println("can not get the file: " + e.toString());
            return build404();
        }
        httpResponse res = new httpResponse("200 OK",fis);
        return res;
    }

    @Override
    public boolean canHandle(String uri) {
        return false;
    }

    @Override
    public httpResponse handle(httpRequest request) {
        return buildResponse();
    }
}
