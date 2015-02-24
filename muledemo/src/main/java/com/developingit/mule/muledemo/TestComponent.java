package com.developingit.mule.muledemo;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
 
public class TestComponent implements Callable{
@Override
    public Object onCall(MuleEventContext eventContext) throws Exception {
 
		//execute some business logic i.e. save to Db, call other service, etc. 		
        eventContext.getMessage().setInvocationProperty("myProperty", "Hello World!");
        // return unchanged payload
        return eventContext.getMessage().getPayload();
    }
}