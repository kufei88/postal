package com.jishengsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



import com.jishengsoft.pojo.Electrifys;

@Mapper
public interface ElectrifyMapper {
	@Select("SELECT * FROM electrifys order by rank")
    List<Electrifys> getAll();
	
	@Insert("insert into electrifys(electrify,rank) values (#{electrify},#{rank}) ")
	int insertElectrifys(Electrifys electrifys);
	
	@Update("update electrifys set electrify=#{electrify},rank=#{rank} where id=#{id}")
    int updateElectrifys(Electrifys electrifys);
	
	@Delete("delete from electrifys where id=#{id}")
	int delElectrifys(int id);
	
	@Select("select  * from electrifys where electrify=#{electrify} limit 1")
	Electrifys getByElectrify(@Param("electrify") String electrify);
	
	@Select("select  * from electrifys where electrify=#{electrify} and id<>#{id} limit 1")
	Electrifys getByElectrifyID(@Param("electrify") String electrify,@Param("id") int id);
}
