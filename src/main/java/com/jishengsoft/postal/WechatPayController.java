package com.jishengsoft.postal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jishengsoft.WXPay.MyConfig;
import com.jishengsoft.WXPay.WXPay;
import com.jishengsoft.WXPay.WXPayUtil;
import com.jishengsoft.mapper.SendApplyMapper;
import com.jishengsoft.mapper.SettingMapper;
import com.jishengsoft.pojo.Setting;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.pojo.TableResult;

@RestController 
@RequestMapping(value="/wechatPay")
public class WechatPayController {
	@Autowired
	private SendApplyMapper sam ;
	@Autowired
	private SettingMapper sm;
	
	@Autowired
    private SimpMessagingTemplate template;
	
	@RequestMapping(value="/getPayCode",method=RequestMethod.GET)
	public Map<String, String> getPayCode(HttpServletRequest request) throws Exception {
		MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
        String billcode = request.getParameter("billcode");
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "一键代发-代发费用");
        data.put("out_trade_no", billcode);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        String sum="100";
        Setting setting = sm.getSettingByName("代发费用");
        if(setting == null){
        	sm.insertSetting("代发费用", "1");
        }
        else{
        	sum = (int)(Float.parseFloat(setting.getValValue())*100)+"";
        }
        System.out.println(sum);
        data.put("total_fee", sum);
        data.put("spbill_create_ip", request.getRemoteAddr());
        data.put("notify_url", "http://wgj9m5.natappfree.cc/wechatPay/getReturnPay");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");

       
        Map<String, String> resp = wxpay.unifiedOrder(data,10000,10000);
        resp.put("total_fee", setting.getValValue());
         //   System.out.println(resp);
        return resp;
	}
	
	@RequestMapping(value="/getReturnPay",method=RequestMethod.POST)
	
	public StringResult getReturnPay(HttpServletRequest request) throws Exception{
		//读取参数  
		StringResult sr = new StringResult();
        InputStream inputStream ;  
        StringBuffer sb = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s ;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null){  
            sb.append(s);  
        }  
        in.close();  
        inputStream.close();
        Map<String,String> result = WXPayUtil.xmlToMap(sb.toString());
        if(result.get("result_code").equals("SUCCESS")){
        	sam.updatePayState(result.get("out_trade_no"));
        	sr.setResult("success");
        }
        template.convertAndSend("/topic/greetings", sr);
        return sr;
	}
}
