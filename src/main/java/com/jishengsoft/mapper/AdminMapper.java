package com.jishengsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jishengsoft.pojo.Admin;


@Mapper
public interface AdminMapper {
	@Select("SELECT * FROM admin")
    List<Admin> getAll();
	
	@Insert("insert into admin(username,password) values (#{username},#{password}) ")
	int insertAdmin(Admin admin);
	
	@Select("select * from admin where username=#{username} and password=#{password} limit 1")
	Admin login(@Param("username") String username,@Param("password") String password);
	
	@Update("update admin set password=#{password} where username=#{username}")
    int updatePass(@Param("username") String username,@Param("password") String password);
}


