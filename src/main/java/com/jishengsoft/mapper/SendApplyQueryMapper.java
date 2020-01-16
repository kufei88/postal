package com.jishengsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jishengsoft.pojo.SendApplyQueryGroup;

@Mapper
public interface SendApplyQueryMapper {
	
	@Select("select a.username,b.name,b.school,b.number,count(*) as count,sum(case when ifnull(dealMemo,'')='异常' then 1 else 0 end) as error"+
			",sum(case when ifnull(dealMemo,'')='异常' then 0 else 1 end) as normal "+
			"from sendapply a left join users b on a.username=b.username where a.username like #{search} "+
			"or  b.name like #{search1} or b.school like #{search2} or b.number like #{search3}  group by a.username,b.name,b.school,b.number limit #{pageindex},#{pageend}")
	List<SendApplyQueryGroup> groupByUser(@Param("pageindex") int pageindex,@Param("pageend") int pageend,@Param("search") String search
			,@Param("search1") String search1,@Param("search2") String search2,@Param("search3") String search3);
	
	@Select("select count(*) as count from (select a.username from sendapply a left join users b on a.username=b.username  where a.username like #{search} "+
			"or  b.name like #{search1} or b.school like #{search2} or b.number like #{search3}   group by a.username,b.name,b.school,b.number) a")
	int groupByUserCount(@Param("search") String search
			,@Param("search1") String search1,@Param("search2") String search2,@Param("search3") String search3);
	
	@Select("select b.school,count(*) as count,sum(case when ifnull(dealMemo,'')='异常' then 1 else 0 end) as error"+
			",sum(case when ifnull(dealMemo,'')='异常' then 0 else 1 end) as normal "+
			"from sendapply a left join users b on a.username=b.username where  b.school like #{search2}   group by b.school limit #{pageindex},#{pageend}")
	List<SendApplyQueryGroup> groupBySchool(@Param("pageindex") int pageindex,@Param("pageend") int pageend,@Param("search2") String search2);
	
	@Select("select count(*) as count from (select b.school from sendapply a left join users b on a.username=b.username  where "+
	"b.school like #{search2}   group by b.school) a")
	int groupBySchoolCount(@Param("search2") String search2);
	
	@Select("select DATE_FORMAT(billDate,'%Y-%m-%d') as username ,count(*) as count,sum(case when ifnull(dealMemo,'')='异常' then 1 else 0 end) as error"+
			",sum(case when ifnull(dealMemo,'')='异常' then 0 else 1 end) as normal "+
			"from sendapply where billDate>#{begindate} and DATE_FORMAT(billDate,'%Y-%m-%d')<=#{enddate}  GROUP BY DATE_FORMAT(billDate,'%Y-%m-%d') "+
			"order by username  limit #{pageindex},#{pageend}")
	List<SendApplyQueryGroup> groupByDate(@Param("pageindex") int pageindex,@Param("pageend") int pageend,
			@Param("begindate") String begindate,@Param("enddate") String enddate);
	
	@Select("select count(*) as count from (select DATE_FORMAT(billDate,'%Y-%m-%d') as bd from sendapply "+
	" where billDate>#{begindate} and DATE_FORMAT(billDate,'%Y-%m-%d')<=#{enddate}  group by DATE_FORMAT(billDate,'%Y-%m-%d')) a")
			int groupByDateCount(@Param("begindate") String begindate,@Param("enddate") String enddate);
	
	@Select("select DATE_FORMAT(billDate,'%Y-%m') as username ,count(*) as count,sum(case when ifnull(dealMemo,'')='异常' then 1 else 0 end) as error"+
			",sum(case when ifnull(dealMemo,'')='异常' then 0 else 1 end) as normal "+
			"from sendapply where billDate>#{begindate} and DATE_FORMAT(billDate,'%Y-%m-%d')<=#{enddate}  GROUP BY DATE_FORMAT(billDate,'%Y-%m') "+
			"order by username  limit #{pageindex},#{pageend}")
	List<SendApplyQueryGroup> groupByMonth(@Param("pageindex") int pageindex,@Param("pageend") int pageend,
			@Param("begindate") String begindate,@Param("enddate") String enddate);
	
	@Select("select count(*) as count from (select DATE_FORMAT(billDate,'%Y-%m') as bd from sendapply "+
	" where billDate>#{begindate} and DATE_FORMAT(billDate,'%Y-%m-%d')<=#{enddate}  group by DATE_FORMAT(billDate,'%Y-%m')) a")
			int groupByMonthCount(@Param("begindate") String begindate,@Param("enddate") String enddate);

}
