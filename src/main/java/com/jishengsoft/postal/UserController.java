package com.jishengsoft.postal;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.jishengsoft.mapper.NumberCodeMapper;
import com.jishengsoft.mapper.UserMapper;
import com.jishengsoft.pojo.NumberCode;
import com.jishengsoft.pojo.SendApply;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.pojo.User;
import com.jishengsoft.util.MD5;
import com.jishengsoft.util.Quantity;

@RestController 
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下 
public class UserController { 
 
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private NumberCodeMapper ncm;
 
    
    
    @RequestMapping(value="/getLoginUser", method=RequestMethod.GET) 
    public StringResult getUserList(HttpServletRequest request) { 
        StringResult sr = new StringResult();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Quantity.USERNAME_KEY);
        sr.setResult(username);
        return sr;
    } 
    
    @RequestMapping(value="/getUserInformation", method=RequestMethod.GET) 
    public User getUserInformation(HttpServletRequest request) { 
        User user = new User();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Quantity.USERNAME_KEY);
        
        if(username == null){
        	user.setUsername("timeout");
        	return user;
        }
        else{
        	user = userMapper.findByUsername(username);
        	user.setPassword("");
        }
        
        return user;
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET) 
    public StringResult login(HttpServletRequest request){
    	StringResult sr= new StringResult();
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String vericode = request.getParameter("vericode");
    	HttpSession session = request.getSession();
    	String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY); 
    	if(!code.equals(vericode.toLowerCase())){
    		sr.setResult("codeerr");
    		return sr;
    	}
    	User user = userMapper.login(username, MD5.getMD5(password));
    	if(user == null){
    		sr.setResult("failed");
    	}
    	else{
    		sr.setResult("success");
    		session.setAttribute(Quantity.USERNAME_KEY, username);
    	}
    	return sr;
    }
 
    @RequestMapping(value="/", method=RequestMethod.POST) 
    public StringResult postUser(@ModelAttribute User user) { 
        // 处理"/users/"的POST请求，用来创建User 
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数 
    	StringResult sr = new StringResult();
    	
    	User user1 = userMapper.findByName(user.getUsername());
    	if(user1 != null){
    		sr.setResult("exists");
    		return sr;
    	}
    	
    	NumberCode nc = ncm.findByNumber(user.getNumber());
    	if((nc==null)||(!nc.getCode().equals(user.getNumberCode()))){
    		sr.setResult("codeerr");
    		return sr;
    	}
    	
    	user.setPassword(MD5.getMD5(user.getPassword()));
    	userMapper.insert(user);
    	sr.setResult("success");
    	//System.out.println(user.getNumberCode());
        //userMapper.insert(user);
        return sr; 
    } 
    
    @RequestMapping(value="/checkUsername/{username}", method=RequestMethod.GET) 
    public StringResult checkUsername(@PathVariable String username){
    	StringResult sr = new StringResult();
    	
    	User user = userMapper.findByName(username);
    	if(user == null){
    		sr.setResult("success");
    	}
    	else{
    		sr.setResult("failed");
    	}
    	return sr;
    }
    
    @RequestMapping(value="/checkNumber/{number}", method=RequestMethod.GET) 
    public StringResult checkNumber(@PathVariable String number){
    	StringResult sr = new StringResult();
    	
    	User user = userMapper.findByNumber(number);
    	if(user == null){
    		sr.setResult("success");
    	}
    	else{
    		sr.setResult("failed");
    	}
    	return sr;
    }
    
    
 
    @RequestMapping(value="/{id}", method=RequestMethod.GET) 
    public User getUser(@PathVariable Long id) { 
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息 
        // url中的id可通过@PathVariable绑定到函数的参数中 
        return userMapper.findById(id); 
    } 
 
    @RequestMapping(value="/{id}", method=RequestMethod.PUT) 
    public String putUser(@PathVariable Long id, @ModelAttribute User user) { 
        // 处理"/users/{id}"的PUT请求，用来更新User信息 
        User u = new User(); 
        u.setName(user.getName()); 
        u.setAge(user.getAge()); 
        u.setId(id);
        userMapper.update(user);
        return "success"; 
    } 
    
    @RequestMapping(value="/updatePass", method=RequestMethod.PUT) 
    public StringResult updatePass(HttpServletRequest request) { 
        // 处理"/users/{id}"的PUT请求，用来更新User信息 
    	StringResult sr = new StringResult();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.USERNAME_KEY);
    	if(username == null){
    		sr.setResult("timeout");
    		return sr;
    	}
    	String password = request.getParameter("password");
    	String newpassword = request.getParameter("newpassword");
    	User user = userMapper.login(username, MD5.getMD5(password));
    	if(user == null){
    		sr.setResult("failed");
    		return sr;
    	}
    	
    	userMapper.updatePass(username,MD5.getMD5(newpassword));
    	sr.setResult("success");
        return sr; 
    } 
    
    @RequestMapping(value="/forgetPass", method=RequestMethod.PUT) 
    public StringResult forgetPass(HttpServletRequest request) { 
        // 处理"/users/{id}"的PUT请求，用来更新User信息 
    	StringResult sr = new StringResult();
    	
    	String password = request.getParameter("password");
    	String username = request.getParameter("username");
    	String number = request.getParameter("number");
    	String numberCode = request.getParameter("numberCode");
    	NumberCode nc = ncm.findByNumber(number);
    	
    	if((nc==null)||(!nc.getCode().equals(numberCode))){
    		sr.setResult("codeerr");
    		return sr;
    	}
    	
    	userMapper.updatePass(username,MD5.getMD5(password));
    	sr.setResult("success");
        return sr; 
    } 
    
    
    
    @RequestMapping(value="/updateInformation", method=RequestMethod.PUT) 
    public StringResult updateInformation(HttpServletRequest request,@ModelAttribute User user) { 
        // 处理"/users/{id}"的PUT请求，用来更新User信息 
    	StringResult sr = new StringResult();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.USERNAME_KEY);
    	if(username == null){
    		sr.setResult("timeout");
    		return sr;
    	}
    	
    	user.setUsername(username);
    	
    	User user1 = userMapper.findByUsername(username);
    	if(user1 == null){
    		sr.setResult("timeout");
    		return sr;
    	}
    	if(!user1.getNumber().equals(user.getNumber())){
    		NumberCode nc = ncm.findByNumber(user.getNumber());
        	if((nc==null)||(!nc.getCode().equals(user.getNumberCode()))){
        		sr.setResult("codeerr");
        		return sr;
        	}
        	else{
        		userMapper.updateInformation(user);
        	}
    	}
    	else{
    		userMapper.updateInformation(user);
    	}
    	
    	sr.setResult("success");
        return sr; 
    } 
 
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
    public String deleteUser(@PathVariable Long id) { 
        // 处理"/users/{id}"的DELETE请求，用来删除User 
        userMapper.delete(id);
        return "success"; 
    } 
 
}
