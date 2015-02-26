package com.developingit.mule.muledemo;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
 
public class TestTransformer extends AbstractMessageTransformer{
    /**
     * @param args
     */
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
            
    	String payload = message.getPayload(String.class);
    	payload = payload.replace("order", "a-order");
    	return payload;
    }
}