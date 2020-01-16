package com.jishengsoft.postal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jishengsoft.mapper.InterLogitscsMapper;

import com.jishengsoft.pojo.Electrifys;
import com.jishengsoft.pojo.InterLogitscs;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.pojo.TableResult;

@RestController
@RequestMapping(value = "/interLogistscs")
public class InterLogistscsController {
	@Autowired
	private InterLogitscsMapper interLogitscsMapper;
	
	@RequestMapping(value="/getInterLogitscs",method= RequestMethod.GET)
	public TableResult getInterLogitscs(){
		TableResult tr = new TableResult();
		List<InterLogitscs> aes = interLogitscsMapper.getAll();
		tr.setRel(true);
    	tr.setMsg("获取成功");
    	tr.setList((ArrayList) aes);
    	tr.setCount(aes.size());
		return tr;
	}
	
	@RequestMapping(value="/getInterLogitscsByElec",method= RequestMethod.GET)
	public List<InterLogitscs> getElectrifys(HttpServletRequest request){
		String electrify =request.getParameter("electrify");
		ArrayList<InterLogitscs> is = (ArrayList)interLogitscsMapper.getInterLogitscsByElec(Integer.parseInt(electrify));
		
		
		return is;
	}
	
	@RequestMapping(value="/addInterLogitscs",method=RequestMethod.POST)
	public StringResult addInterLogitscs(@ModelAttribute InterLogitscs interLogitscs){
		StringResult sr = new StringResult();
		InterLogitscs e = interLogitscsMapper.getByinterLogitsc(interLogitscs.getInterLogitsc());
		if (e != null){
			sr.setResult("exists");
			return sr;
		}
		
		interLogitscsMapper.insertInterLogitscs(interLogitscs);
		sr.setResult("success");
		return sr;
	}
	
	@RequestMapping(value="/updateInterLogitscs",method=RequestMethod.PUT)
	public StringResult updateInterLogitscs(@ModelAttribute InterLogitscs interLogitscs){
		StringResult sr = new StringResult();
		InterLogitscs e = interLogitscsMapper.getByinterLogitsID(interLogitscs.getInterLogitsc(),interLogitscs.getId());
		if (e != null){
			sr.setResult("exists");
			return sr;
		}
		
		interLogitscsMapper.updateInterLogitscs(interLogitscs);
		sr.setResult("success");
		return sr;
	}
	
	@RequestMapping(value="/delInterLogitscs",method=RequestMethod.POST)
	public StringResult delInterLogitscs(HttpServletRequest request){
		StringResult sr = new StringResult();
		int id = Integer.parseInt(request.getParameter("id"));
		
		interLogitscsMapper.delInterLogitscs(id);
		sr.setResult("success");
		return sr;
	}
}
