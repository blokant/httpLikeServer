package com.trenkinan.experiments.httplikeserver;

import com.trenkinan.restexperiments.tasksHandler;

/**
 * Created by trenkinan on 25.10.14.
 */
public class Main {
    public static void main(String[] args) {
       // com.trenkinan.experiments.httplikeserver.httpFileHelper hfh = new com.trenkinan.experiments.httplikeserver.httpFileHelper("test.html");
       // System.out.println(new String(hfh.buildResponse().getBytes()));
        Server server = new Server("/home/trenkinan/work/httpRoot/", 8081);
        server.addHandler(new tasksHandler());
        server.start();

    }
}
