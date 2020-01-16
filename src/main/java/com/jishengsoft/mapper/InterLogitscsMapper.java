package com.jishengsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.jishengsoft.pojo.InterLogitscs;

@Mapper
public interface InterLogitscsMapper {  
	@Select("SELECT a.interLogitsc,a.electrifysId,a.storage,a.id,b.electrify FROM InterLogistscs a left join electrifys b on a.electrifysId=b.id")
    List<InterLogitscs> getAll();
	
	@Select("select interLogitsc,id,storage from interLogistscs where electrifysid in (select id from electrifys"+
	" where rank >= (select rank from electrifys where id=#{electrify}))")
	List<InterLogitscs> getInterLogitscsByElec(int electrify);
	
	@Insert("insert into InterLogistscs(interLogitsc,electrifysId,storage) values (#{interLogitsc},#{electrifysId},#{storage}) ")
	int insertInterLogitscs(InterLogitscs interLogitscs);
	
	@Update("update InterLogistscs set interLogitsc=#{interLogitsc},electrifysId=#{electrifysId},storage=#{storage} where id=#{id}")
    int updateInterLogitscs(InterLogitscs interLogitscs);
	
	@Delete("delete from InterLogistscs where id=#{id}")
	int delInterLogitscs(int id);
	
	@Select("select  * from InterLogistscs where interLogitsc=#{interLogitsc} limit 1")
	InterLogitscs getByinterLogitsc(@Param("interLogitsc") String interLogitsc);
	
	@Select("select  * from InterLogistscs where interLogitsc=#{interLogitsc} and id<>#{id} limit 1")
	InterLogitscs getByinterLogitsID(@Param("interLogitsc") String interLogitsc,@Param("id") int id);
}
