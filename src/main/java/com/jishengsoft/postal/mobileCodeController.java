package com.jishengsoft.postal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jishengsoft.mapper.NumberCodeMapper;
import com.jishengsoft.mapper.UserMapper;
import com.jishengsoft.pojo.NumberCode;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.pojo.User;
import com.jishengsoft.util.MyHttpClient;

@RestController 
@RequestMapping(value="/mobileCode")
public class mobileCodeController {
	@Autowired
	private NumberCodeMapper ncm;
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value="/generationCode", method=RequestMethod.GET) 
	public StringResult generationCode(HttpServletRequest request){
		StringResult sr = new StringResult();
		String number = request.getParameter("number");
		User user = userMapper.findByNumber(number);
		if(user != null){
			sr.setResult("numberExists");
			return sr;
		}
		
		NumberCode nc= ncm.findByNumber(number);
		if(nc == null){
			NumberCode newnc = new NumberCode();
			newnc.setNumber(number);
			Random random = new Random();
			try {
				int s = random.nextInt(999999)%(999999-100000+1) + 100000;
				MyHttpClient mhc = new MyHttpClient();
				mhc.SendSms(number, "尊敬的客户你好,你获取的验证码为:"+s+"【济胜软件】");
				
				newnc.setCode(s+"");
				ncm.insert(newnc);
				
				
				sr.setResult("success");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
		else{
			sr.setResult("exists");
		}
		return sr;
	}
	
	@RequestMapping(value="/forgetPassCode", method=RequestMethod.GET) 
	public StringResult forgetPassCode(HttpServletRequest request){
		StringResult sr = new StringResult();
		String number = request.getParameter("number");
		String username = request.getParameter("username");
		User user = userMapper.findByNumberAndUser(number,username);
		if(user == null){
			sr.setResult("numberExists");
			return sr;
		}
		
		NumberCode nc= ncm.findByNumber(number);
		if(nc == null){
			NumberCode newnc = new NumberCode();
			newnc.setNumber(number);
			Random random = new Random();
			try {
				int s = random.nextInt(999999)%(999999-100000+1) + 100000;
				MyHttpClient mhc = new MyHttpClient();
				mhc.SendSms(number, "尊敬的客户你好,你获取的验证码为:"+s+"【济胜软件】");
				
				newnc.setCode(s+"");
				ncm.insert(newnc);
				
				
				sr.setResult("success");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
		else{
			sr.setResult("exists");
		}
		return sr;
	}
}
