package com.trenkinan.experiments.httplikeserver;

/**
 * Created by trenkinan on 02.05.15.
 */
public interface HttpRequestHandler {
    public boolean canHandle(String uri);
    public httpResponse handle(httpRequest request);
}
