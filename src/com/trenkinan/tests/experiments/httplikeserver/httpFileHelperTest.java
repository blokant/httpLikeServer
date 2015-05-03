package com.trenkinan.tests.experiments.httplikeserver;

import static org.junit.Assert.*;
import com.trenkinan.experiments.httplikeserver.httpFileHelper;
/**
 * Created by trenkinan on 03.05.15.
 */
public class httpFileHelperTest {

    @org.junit.Test
    public void testBuildResponse() throws Exception {
        
    }

    @org.junit.Test
    public void testCanHandle() throws Exception {
        assertEquals(true, new httpFileHelper("some/file/path").canHandle("/some/another/file"));
    }

    @org.junit.Test
    public void testHandle() throws Exception {

    }
}