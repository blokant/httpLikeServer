package com.trenkinan.experiments.httplikeserver;

/**
 * Created by trenkinan on 02.05.15.
 */


import java.util.ArrayList;

/**
 * It's a controller: it gets a request and decides how to deal with it. It contains the list of IRequestHandlers
 * Every handler should have regexp that accords to it. So , you can write handler for "*.php" and pass through your
 * interpreter. Or you can get API for /api/tasks/v1/*
 */
public class Switcher {
    private ArrayList<HttpRequestHandler> handlers;
    public Switcher(){
        handlers = new ArrayList<HttpRequestHandler>();
        //TODO how to populate this list right?
    }
    public httpResponse handle(httpRequest request){
        for(HttpRequestHandler handler: handlers){
            if(handler.canHandle(request.getRequestedFileUri())){
                return handler.handle(request);
            }
        }
        return  new httpFileHelper(request.getRequestedFileUri()).buildResponse();
    }
}
