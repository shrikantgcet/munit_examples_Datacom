package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {
	
	@Rule
	public DynamicPort dynamicPort=new DynamicPort("http.port");

    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
    	System.out.println("\n\n ********** > Dynamic port used in JUnit Test Cases #1"+dynamicPort.getNumber()+"\n\n");
        runFlowAndExpect("mavenFlow", "Hello Maven");
    }
    
    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
    	System.out.println("\n\n ********** > Dynamic port used in JUnit Test Cases #2"+dynamicPort.getNumber()+"\n\n");
      MuleEvent event = runFlow("mavenFlow");
      String contentType = event.getMessage().getOutboundProperty("Content-Type");
      assertEquals("application/json", contentType);
    }
    
    
    @Override
    protected String[] getConfigFiles() {
        String[] files = {"mavensetup.xml", "global.xml"};
        return files;
        //return "mavensetup.xml";
}
}