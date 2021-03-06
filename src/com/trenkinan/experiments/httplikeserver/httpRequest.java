package com.trenkinan.experiments.httplikeserver;

import java.util.List;

/**
 * Created by trenkinan on 30.10.14.
 */
public class httpRequest {
    public enum HttpMethods {
        GET, POST, PUT, UPDATE, DELETE
    };
    private final String requestedFileUri;
    private final HttpMethods httpMethod;
    public String getRequestedFileUri() {return requestedFileUri;}
    //TODO getRelative path to begin with /api/...
    public httpRequest(List<String> rawData, String serverRoot) {
        if(rawData.size() < 1) {
            System.out.println("rawData size < 1");
            requestedFileUri = null;
            httpMethod = HttpMethods.GET;
            return;
        }
        String methodString = rawData.get(0).substring(0,3);
        if(methodString.equals("GET")) {
            httpMethod = HttpMethods.GET;
            int firstSpacePosition = rawData.get(0).indexOf(' ') + 1;
            int secondSpacePosition = rawData.get(0).indexOf(' ', firstSpacePosition+1);
            //System.out.println("first: " + firstSpacePosition + " second: " + secondSpacePosition);
            String resourceUri = rawData.get(0).substring(firstSpacePosition, secondSpacePosition);
            //com.trenkinan.experiments.httplikeserver.httpFileHelper fh = new com.trenkinan.experiments.httplikeserver.httpFileHelper(serverRoot + resourceUri);
            //System.out.println("resourceUri: " + resourceUri);
            this.requestedFileUri = serverRoot + resourceUri.substring(1);
        }
        else {
            httpMethod = HttpMethods.POST;
            this.requestedFileUri = null;
        }
    }


}