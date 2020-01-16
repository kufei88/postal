package com.jishengsoft.postal;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.jishengsoft.mapper.SendApplyQueryMapper;
import com.jishengsoft.pojo.PageStruct;
import com.jishengsoft.pojo.SendApplyQueryGroup;

import com.jishengsoft.util.Quantity;

@RestController
@RequestMapping(value = "/sendApplyQuery")
public class SendApplyQueryController {
	@Autowired
	private SendApplyQueryMapper adqm;
	
	@RequestMapping(value="/groupByUser", method=RequestMethod.GET) 
    public PageStruct groupByUser(HttpServletRequest request) { 
		int pageNumber = Integer.parseInt(request
				.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("offset"));
		String search = request.getParameter("search")==null?"":request.getParameter("search");
		PageStruct ps = new PageStruct();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
        if (username == null){
        	return ps;
        }
        List<SendApplyQueryGroup> lsaqg = adqm.groupByUser(page,pageNumber,"%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%");
        ps.setRows(lsaqg);
        ps.setTotal(adqm.groupByUserCount("%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%"));
        return ps;
    } 
	
	
	@RequestMapping(value="/groupBySchool", method=RequestMethod.GET) 
    public PageStruct groupBySchool(HttpServletRequest request) { 
		int pageNumber = Integer.parseInt(request
				.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("offset"));
		String search = request.getParameter("search")==null?"":request.getParameter("search");
		PageStruct ps = new PageStruct();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
        if (username == null){
        	return ps;
        }
        List<SendApplyQueryGroup> lsaqg = adqm.groupBySchool(page,pageNumber,"%"+search+"%");
        ps.setRows(lsaqg);
        ps.setTotal(adqm.groupBySchoolCount("%"+search+"%"));
        return ps;
    }
	
	@RequestMapping(value="/groupByDate", method=RequestMethod.GET) 
    public PageStruct groupByDate(HttpServletRequest request) { 
		int pageNumber = Integer.parseInt(request
				.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("offset"));
		String beginDate = request.getParameter("begindate");
		String endDate = request.getParameter("enddate");
		//String search = request.getParameter("search")==null?"":request.getParameter("search");
		PageStruct ps = new PageStruct();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
        if (username == null){
        	return ps;
        }
        List<SendApplyQueryGroup> lsaqg = adqm.groupByDate(page,pageNumber,beginDate,endDate);
        ps.setRows(lsaqg);
        ps.setTotal(adqm.groupByDateCount(beginDate,endDate));
        return ps;
    }
	
	@RequestMapping(value="/groupByMonth", method=RequestMethod.GET) 
    public PageStruct groupByMonth(HttpServletRequest request) { 
		int pageNumber = Integer.parseInt(request
				.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("offset"));
		String beginDate = request.getParameter("begindate");
		String endDate = request.getParameter("enddate");
		//String search = request.getParameter("search")==null?"":request.getParameter("search");
		PageStruct ps = new PageStruct();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Quantity.ADMIN_KEY);
        if (username == null){
        	return ps;
        }
        List<SendApplyQueryGroup> lsaqg = adqm.groupByMonth(page,pageNumber,beginDate,endDate);
        ps.setRows(lsaqg);
        ps.setTotal(adqm.groupByMonthCount(beginDate,endDate));
        return ps;
    }
	
}
