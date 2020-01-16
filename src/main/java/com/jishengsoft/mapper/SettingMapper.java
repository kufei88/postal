package com.jishengsoft.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jishengsoft.pojo.Setting;

@Mapper
public interface SettingMapper {
	@Select("select * from setting where valName=#{valName}")
	Setting getSettingByName(@Param("valName") String valName);
	
	@Insert("insert into setting (valName,valValue) values (#{valName},#{valValue})")
	int insertSetting(@Param("valName") String valName,@Param("valValue") String valValue);
	
	@Update("update setting set valValue=#{valValue} where valName=#{valName}")
	int updateSetting(Setting setting);
}
