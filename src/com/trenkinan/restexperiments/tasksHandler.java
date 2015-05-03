package com.trenkinan.restexperiments;

import com.trenkinan.experiments.httplikeserver.httpRequest;
import com.trenkinan.experiments.httplikeserver.httpResponse;
import com.trenkinan.experiments.httplikeserver.HttpRequestHandler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by trenkinan on 02.05.15.
 */
public class tasksHandler implements HttpRequestHandler {
    @Override
    public boolean canHandle(String uri) {
        if(uri.contains("/api/tasks"))
            return true;
        return false;
    }

    @Override
    public httpResponse handle(httpRequest request) {
        InputStream stream = new ByteArrayInputStream("This is a Fake task".getBytes(StandardCharsets.UTF_8));
        httpResponse r = new httpResponse("200", stream);
        return r;
    }
}
