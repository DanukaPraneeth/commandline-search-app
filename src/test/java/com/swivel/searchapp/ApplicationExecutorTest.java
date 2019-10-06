package com.swivel.searchapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class ApplicationExecutorTest {

    private ApplicationExecutor applicationExecutor;

    @Before
    public void setUp() throws Exception {
        applicationExecutor = new ApplicationExecutor();
    }

    @Test
    public void check_for_propertyFile_availability() {
        assertTrue(applicationExecutor.readPropertyFile());
    }
}