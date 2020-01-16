package com.jishengsoft.postal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jishengsoft.mapper.SendApplyMapper;
import com.jishengsoft.mapper.SettingMapper;
import com.jishengsoft.pojo.Setting;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.util.Quantity;

@RestController 
@RequestMapping(value="/setting")
public class SettingController {
	@Autowired
	private SettingMapper sm ;
	
	@RequestMapping(value="/getSetting/{valName}",method=RequestMethod.GET)
	public Setting getSetting(@PathVariable String valName){
		Setting st = sm.getSettingByName(valName);
		if(st==null){
			if(valName.equals("代发费用")){
				sm.insertSetting(valName, "1");
				st = sm.getSettingByName(valName);
			}
		}
		return st;
		
	}
	
	@RequestMapping(value="/saveSetting",method=RequestMethod.POST)
	public StringResult saveSetting(@ModelAttribute Setting setting,HttpServletRequest request){
		StringResult sr = new StringResult();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
    	if(username == null){
    		sr.setResult("timeout");
    		return sr;
    	}
		
		sm.updateSetting(setting);
		sr.setResult("success");
		return sr;
	}
}
