package com.trenkinan.experiments.httplikeserver;

/**
 * Created by trenkinan on 02.05.15.
 */

/**
 * Should be implemented if you want to add your behaviour to the server
 * Server has a list of handlers that will be checked when the new request come in.
 * You have to process string uri to decide whether your class can handle this request or not.
 * You can imagen handler for php or ruby files. Or you want to decide to process some special
 * uris like api.myapp/v1.0/* ? 
 */
public interface HttpRequestHandler {
    public boolean canHandle(String uri);
    public httpResponse handle(httpRequest request);
}
