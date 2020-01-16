package com.jishengsoft.pojo;

import java.util.ArrayList;
import java.util.List;

public class TableResult {
	public boolean isRel() {
		return rel;
	}
	public void setRel(boolean rel) {
		this.rel = rel;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private boolean rel;
	private String msg;
	private ArrayList list; 
	private int count;
}
