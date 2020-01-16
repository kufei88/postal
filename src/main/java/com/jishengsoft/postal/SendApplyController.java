package com.jishengsoft.postal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jishengsoft.mapper.SendApplyMapper;
import com.jishengsoft.pojo.SendApply;
import com.jishengsoft.pojo.SendApplyQuery;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.pojo.TableResult;
import com.jishengsoft.pojo.sendApplyResult;
import com.jishengsoft.util.ChineseToEnglish;
import com.jishengsoft.util.DateUtil;
import com.jishengsoft.util.MyHttpClient;
import com.jishengsoft.util.Quantity;


@RestController 
@RequestMapping(value="/sendApply")     // 通过这里配置使下面的映射都在/users下 
public class SendApplyController {
	@Autowired
	private SendApplyMapper sam;
	
	@RequestMapping(value="/", method=RequestMethod.POST) 
    public sendApplyResult postSendApply(HttpServletRequest request,@ModelAttribute SendApply sendApply) { 
        // 处理"/users/"的POST请求，用来创建User 
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数 
		sendApplyResult sr = new sendApplyResult();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.USERNAME_KEY);
    	if(username == null){
    		sr.setResult("timeout");
    		return sr;
    	}
    	String inl = sam.checkInlandCode(sendApply.getInlandCode());
    	if(inl == null){
			
		}
		else{
			sr.setResult("inlandCodeExists");
			return sr;
		}
    	String intl = sam.checkInterCode(sendApply.getInterCode());
    	if(intl == null){
			
		}
		else{
			sr.setResult("interCodeExists");
			return sr;
		}
    	String billcode = sam.selectBillCode();
    	if(billcode == null){
    		billcode = "SD"+DateUtil.getNow("yyyyMMdd")+"0001";
    	}
    	sendApply.setBillcode(billcode);
    	sendApply.setUsername(username);
    	sam.insert(sendApply);
    	sr.setResult("success");
    	sr.setBillcode(billcode);
    	//System.out.println(user.getNumberCode());
        //userMapper.insert(user);
        return sr; 
    }
	
	@RequestMapping(value="/", method=RequestMethod.PUT) 
    public StringResult putSendApply(HttpServletRequest request,@ModelAttribute SendApply sendApply) { 
        // 处理"/users/"的POST请求，用来创建User 
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数 
    	StringResult sr = new StringResult();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.USERNAME_KEY);
    	if(username == null){
    		sr.setResult("timeout");
    		return sr;
    	}
    	String inl = sam.checkInlandCodeById(sendApply.getInlandCode(),sendApply.getId());
    	if(inl == null){
			
		}
		else{
			sr.setResult("inlandCodeExists");
			return sr;
		}
    	String intl = sam.checkInterCodeById(sendApply.getInterCode(),sendApply.getId());
    	if(intl == null){
			
		}
		else{
			sr.setResult("interCodeExists");
			return sr;
		}
    	
    	sam.update(sendApply);
    	sr.setResult("success");
    	//System.out.println(user.getNumberCode());
        //userMapper.insert(user);
        return sr; 
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public StringResult delSendApply(@PathVariable int id){
		sam.delete(id);
		StringResult sr = new StringResult();
		sr.setResult("success");
		return sr;
	}
	
	@RequestMapping(value="/checkInlandCode/{inlandCode}",method=RequestMethod.GET)
	public StringResult checkInLandCode(@PathVariable String inlandCode){
		StringResult sr = new StringResult();
		String inl = sam.checkInlandCode(inlandCode);
		if(inl == null){
			sr.setResult("success");
		}
		else{
			sr.setResult("exists");
		}
		return sr;
	}
	
	@RequestMapping(value="/getSendApplyQuery",method=RequestMethod.GET)
	public TableResult getSendApplyQuery(HttpServletRequest request){
		TableResult tr = new TableResult();
		HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.USERNAME_KEY);
    	int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	String inlandCode = request.getParameter("inlandCode")==null?"":request.getParameter("inlandCode");
    	pageIndex = (pageIndex-1)*pageSize;
    	if(username == null){
    		tr.setRel(false);
    		tr.setMsg("登录过期");
    		return tr;
    	}
    	List<SendApplyQuery> lssq = sam.getSendApplyQuery(username,pageIndex,pageSize,"%"+inlandCode+"%");
    	tr.setRel(true);
    	tr.setMsg("获取成功");
    	tr.setList((ArrayList) lssq);
    	tr.setCount(sam.getCount(username,"%"+inlandCode+"%"));
    	return tr;
	}
	
	@RequestMapping(value="/getMainSendApplyQuery",method=RequestMethod.GET)
	public TableResult getMainSendApplyQuery(HttpServletRequest request){
		TableResult tr = new TableResult();
		HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
    	int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	String seachUser = request.getParameter("username")==null?"":request.getParameter("username");
    	String inlandCode = request.getParameter("inlandCode")==null?"":request.getParameter("inlandCode");
    	String school = request.getParameter("school")==null?"":request.getParameter("school");
    	pageIndex = (pageIndex-1)*pageSize;
    	if(username == null){
    		tr.setRel(false);
    		tr.setMsg("登录过期");
    		return tr;
    	}
    	List<SendApplyQuery> lssq = sam.getMainSendApplyQuery(pageIndex,pageSize,"%"+inlandCode+"%","%"+seachUser+"%","%"+school+"%");
    	tr.setRel(true);
    	tr.setMsg("获取成功");
    	tr.setList((ArrayList) lssq);
    	tr.setCount(sam.getMainCount("%"+inlandCode+"%","%"+seachUser+"%","%"+school+"%"));
    	return tr;
	}
	
	@RequestMapping(value="/getSendApplyByInlandCode/{inlandCode}",method=RequestMethod.GET)
	public SendApplyQuery getSendApplyByInlandCode(HttpServletRequest request,@PathVariable String inlandCode){
		SendApplyQuery saq = new SendApplyQuery();
		HttpSession session = request.getSession();
    	String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
    	
    	if(username == null){
    		saq.setInlandCode("登录过期");
    		return saq;
    	}
    	saq = sam.getSendApplyByInlandCode(inlandCode);
    	
    	return saq;
	}
	
	
	
	@RequestMapping(value="/getFollow",method=RequestMethod.GET)
	public StringResult getFollow(HttpServletRequest request){
		StringResult sr = new StringResult();
		MyHttpClient mhc = new MyHttpClient();
		String code = request.getParameter("nu");
		String logi = request.getParameter("com");
		System.out.println(logi);
		logi = ChineseToEnglish.getPingYin(logi);
		System.out.println(logi);
		String url = mhc.searchkuaiDiInfo(Quantity.KUAIDI_KEY, logi, code);
		sr.setResult(url);
		return sr;
	}
	
	@RequestMapping(value="/updateDealMemo",method=RequestMethod.PUT)
	public StringResult updateDealMemo(HttpServletRequest request){
		StringResult sr = new StringResult();
		String dealMemo = request.getParameter("dealMemo");
		int id = Integer.parseInt(request.getParameter("id"));
		sam.updateDealMemo(dealMemo, id);
		sr.setResult("success");
		return sr;
	}
	
	@RequestMapping(value="/updateState",method=RequestMethod.PUT)
	public StringResult updateState(HttpServletRequest request){
		StringResult sr = new StringResult();
		
		int id = Integer.parseInt(request.getParameter("id"));
		sam.updateState(id);
		sr.setResult("success");
		return sr;
	}
	
}
