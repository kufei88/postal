package com.jishengsoft.postal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.jishengsoft.mapper.ElectrifyMapper;
import com.jishengsoft.pojo.Electrifys;

import com.jishengsoft.pojo.InterLogitscs;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.pojo.TableResult;

@RestController
@RequestMapping(value = "/electrifys")
public class ElectrifysController {
	
	@Autowired
	private ElectrifyMapper electrifyMapper;
	
	@RequestMapping(value="/getElectrifys",method= RequestMethod.GET)
	public TableResult getElectrifys(){
		TableResult tr = new TableResult();
		List<Electrifys> aes = electrifyMapper.getAll();
		tr.setRel(true);
    	tr.setMsg("获取成功");
    	tr.setList((ArrayList) aes);
    	tr.setCount(aes.size());
		return tr;
	}
	
	
	
	
	@RequestMapping(value="/addElectrifys",method=RequestMethod.POST)
	public StringResult addElectrifys(@ModelAttribute Electrifys electrifys){
		StringResult sr = new StringResult();
		Electrifys e = electrifyMapper.getByElectrify(electrifys.getElectrify());
		if (e != null){
			sr.setResult("exists");
			return sr;
		}
		
		electrifyMapper.insertElectrifys(electrifys);
		sr.setResult("success");
		return sr;
	}
	
	@RequestMapping(value="/updateElectrifys",method=RequestMethod.PUT)
	public StringResult updateElectrifys(@ModelAttribute Electrifys electrifys){
		StringResult sr = new StringResult();
		Electrifys e = electrifyMapper.getByElectrifyID(electrifys.getElectrify(),electrifys.getId());
		if (e != null){
			sr.setResult("exists");
			return sr;
		}
		
		electrifyMapper.updateElectrifys(electrifys);
		sr.setResult("success");
		return sr;
	}
	
	@RequestMapping(value="/delElectrifys",method=RequestMethod.POST)
	public StringResult delElectrifys(HttpServletRequest request){
		StringResult sr = new StringResult();
		int id = Integer.parseInt(request.getParameter("id"));
		
		electrifyMapper.delElectrifys(id);
		sr.setResult("success");
		return sr;
	}
	
	
}
