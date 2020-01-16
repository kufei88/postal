package com.jishengsoft.postal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.jishengsoft.pojo.PayMessage;
import com.jishengsoft.pojo.StringResult;

@Controller
public class PayMessageController {
	
	@MessageMapping("/payMessage")
    @SendTo("/topic/greetings")
    public StringResult greeting(PayMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        StringResult sr =new StringResult();
        sr.setResult(message.getName());
        return  sr;
    }
}
