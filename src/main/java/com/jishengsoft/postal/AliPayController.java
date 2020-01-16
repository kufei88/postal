package com.jishengsoft.postal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jishengsoft.AliPay.AliPayConfig;
import com.jishengsoft.WXPay.WXPayUtil;
import com.jishengsoft.mapper.SendApplyMapper;
import com.jishengsoft.mapper.SettingMapper;
import com.jishengsoft.pojo.Setting;

@RestController 
@RequestMapping(value="/aliPay")
public class AliPayController {
	@Autowired
	private SendApplyMapper sam ;
	@Autowired
	private SettingMapper sm;
	
	@Autowired
    private SimpMessagingTemplate template;
	@RequestMapping(value="/getPayCode",method=RequestMethod.GET)
	public void getPayCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String billcode = request.getParameter("billcode");
		String state = sam.selectStateByBillCode(billcode);
		if((state!=null)&&state.equals("已支付")){
			response.setContentType("text/html;charset=" + AliPayConfig.CHARSET);
		    response.getWriter().write("该订单已支付");//直接将完整的表单html输出到页面
		    response.getWriter().flush();
		    response.getWriter().close();
		}
		AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.URL,AliPayConfig.APP_ID,
				AliPayConfig.APP_PRIVATE_KEY,AliPayConfig.FORMAT,
				AliPayConfig.CHARSET,AliPayConfig.ALIPAY_PUBLIC_KEY,AliPayConfig.SIGN_TYPE);
		
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
	    alipayRequest.setReturnUrl("http://wgj9m5.natappfree.cc/");
	    alipayRequest.setNotifyUrl("http://wgj9m5.natappfree.cc/aliPay/getReturnPay");//在公共参数中设置回跳和通知地址
	    String sum="1";
        Setting setting = sm.getSettingByName("代发费用");
        if(setting == null){
        	sm.insertSetting("代发费用", "1");
        }
        else{
        	sum = setting.getValValue();
        }
	    alipayRequest.setBizContent("{" +
	        "    \"out_trade_no\":\""+billcode+"\"," +
	        "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	        "    \"total_amount\":"+sum+"," +
	        "    \"subject\":\"一键代发(代发费用)\"," +
	        "    \"body\":\"代发费用\"," +
	        "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
	        "    \"extend_params\":{" +
	        "    \"sys_service_provider_id\":\"2088511833207846\"" +
	        "    }"+
	        "  }");//填充业务参数
	    String form="";
	    try {
	        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	    }
	    response.setContentType("text/html;charset=" + AliPayConfig.CHARSET);
	    response.getWriter().write(form);//直接将完整的表单html输出到页面
	    response.getWriter().flush();
	    response.getWriter().close();
	}
	
	@RequestMapping(value="/getReturnPay",method=RequestMethod.POST)
	public void getReturnPay(HttpServletRequest request) throws Exception{
		//读取参数  
        String out_trade_no = request.getParameter("out_trade_no");
        if(out_trade_no != null){
        	sam.updatePayState(out_trade_no);
        	template.convertAndSend("/topic/greetings", "");
        }
        
	}
}
