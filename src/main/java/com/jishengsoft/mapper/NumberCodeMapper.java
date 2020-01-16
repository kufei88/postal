package com.jishengsoft.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jishengsoft.pojo.NumberCode;
import com.jishengsoft.pojo.User;

@Mapper
public interface NumberCodeMapper {
	
	
	@Select("SELECT * FROM numberCode WHERE number = #{number} and now()-codetime<120")
    NumberCode findByNumber(@Param("number") String number);
	
	@Insert("INSERT INTO numberCode(number, code,codetime) VALUES(#{number}, #{code},now())")
    int insert(NumberCode numbercode);
}
