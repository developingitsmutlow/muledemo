/*
 * Generated by the Mule project wizard. http://mule.mulesoft.org
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.developingit.mule.muledemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.transport.NullPayload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MuleDemoTestCase extends FunctionalTestCase
{
	
	private static String MESSAGE = "Hello World!";
	
    protected String getConfigResources()
    {
        //TODO You'll need to edit this file to make the test applicable to your module
        return "muledemo-functional-test-config.xml";
        //return "mule-config.xml";
    }

    @Test
    public void muledemo() throws Exception
    {
        MuleClient client = muleContext.getClient();
        client.dispatch("vm://in", "some data", null);

        MuleMessage result = client.request("vm://out", RECEIVE_TIMEOUT);
        assertNotNull(result);
        assertNull(result.getExceptionPayload());
        assertFalse(result.getPayload() instanceof NullPayload);

        //TODO Assert the correct data has been received
        assertEquals("some data Received", result.getPayloadAsString());
    }
    
    @Test
    @Ignore
    public void muledemohttp() throws Exception
    {
    	MuleClient client = muleContext.getClient();
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("http.method", "GET"); 
        MuleMessage result = client.send("http://localhost:8081", "", props);
        assertNotNull(result);
        assertFalse(result.getPayload() instanceof NullPayload);
        assertEquals(MESSAGE, result.getPayloadAsString());
    }
    
    @Test
    @Ignore
    public void muledemohttpsoap() throws Exception
    {
    	MuleClient client = muleContext.getClient();
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("http.method", "POST");
        props.put("Content-type", "application/xml");
        //props.put("Accept", "text/plain");
        String payload = "<tsh:OrderTshirt xmlns:tsh=\"http://mulesoft.org/tshirt-service\"><size>S</size><email>sddcddddfaaddw2@sdfco.com</email><name>dsf</name><address1>sdsfd</address1><address2>sdf</address2><city>dsfs</city><stateOrProvince>sdfs</stateOrProvince><postalCode>34345</postalCode><country>Italy</country></tsh:OrderTshirt>";
        
        Random rnd = new Random();
        //payload = payload.replace("_random_", String.valueOf(100000 + rnd.nextInt(900000)));
        System.out.println(payload);
        //MuleMessage req = MuleMessage
        MuleMessage result = client.send("http://localhost:8081/", "ASDSA", props);
        assertNotNull(result);
        assertFalse(result.getPayload() instanceof NullPayload);
        assertTrue(result.getPayloadAsString().contains("orderId"));
    }
}
