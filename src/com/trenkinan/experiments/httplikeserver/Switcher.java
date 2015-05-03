package com.trenkinan.experiments.httplikeserver;

/**
 * Created by trenkinan on 02.05.15.
 */


import java.util.ArrayList;
import java.util.List;

/**
 * It's a controller: it gets a request and decides how to deal with it. It contains the list of IRequestHandlers
 * Every handler should have regexp that accords to it. So , you can write handler for "*.php" and pass through your
 * interpreter. Or you can get API for /api/tasks/v1/*
 */
public class Switcher {
    private List<HttpRequestHandler> handlers;
    public Switcher(List<HttpRequestHandler> handlerList){
        this.handlers = handlerList;
        if(this.handlers == null)
            handlers = new ArrayList<HttpRequestHandler>();
        //TODO how to populate this list right?
    }
    public httpResponse handle(httpRequest request){
        System.out.println("Trying to handle: " + request.getRequestedFileUri());
        for(HttpRequestHandler handler: handlers){
            if(handler.canHandle(request.getRequestedFileUri())){
                System.out.println("Handler can handle it: " + handler.getClass().toString());
                return handler.handle(request);
            }
        }
        System.out.println("No one handler can deal with it, so, file helper will do the job");
        return  new httpFileHelper(request.getRequestedFileUri()).buildResponse();
    }
}
