package com.jishengsoft.postal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.jishengsoft.mapper.AdminMapper;

import com.jishengsoft.pojo.Admin;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.pojo.User;

import com.jishengsoft.util.MD5;
import com.jishengsoft.util.Quantity;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private AdminMapper adminMapper;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public StringResult login(HttpServletRequest request) {
		StringResult sr = new StringResult();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String vericode = request.getParameter("vericode");
		HttpSession session = request.getSession();
		String code = (String) session
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!code.equals(vericode.toLowerCase())) {
			sr.setResult("codeerr");
			return sr;
		}
		
		List<Admin> la = adminMapper.getAll();
		
		if(la.size() == 0){
			Admin a = new Admin();
			a.setUsername("admin");
			a.setPassword(MD5.getMD5("admin"));
			adminMapper.insertAdmin(a);
		}
		
		
		Admin aa = adminMapper.login(username, MD5.getMD5(password));
		if (aa == null) {
			sr.setResult("failed");
		} else {
			sr.setResult("success");
			session.setAttribute(Quantity.ADMIN_KEY, username);
		}
		return sr;
	}
	
	@RequestMapping(value="/updatePass", method=RequestMethod.PUT) 
    public StringResult updatePass(HttpServletRequest request) { 
        // 处理"/users/{id}"的PUT请求，用来更新User信息 
    	StringResult sr = new StringResult();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
    	if(username == null){
    		sr.setResult("timeout");
    		return sr;
    	}
    	String password = request.getParameter("password");
    	String newpassword = request.getParameter("newpassword");
    	Admin user = adminMapper.login(username, MD5.getMD5(password));
    	if(user == null){
    		sr.setResult("failed");
    		return sr;
    	}
    	
    	adminMapper.updatePass(username,MD5.getMD5(newpassword));
    	sr.setResult("success");
        return sr; 
    } 
	
	@RequestMapping(value="/getLoginUser", method=RequestMethod.GET) 
    public StringResult getUserList(HttpServletRequest request) { 
        StringResult sr = new StringResult();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
        sr.setResult(username);
        return sr;
    } 
}
