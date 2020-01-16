package com.jishengsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



import com.jishengsoft.pojo.SendApply;
import com.jishengsoft.pojo.SendApplyQuery;

@Mapper
public interface SendApplyMapper {
	@Insert("INSERT INTO sendApply(username, electricity,inlandLogistics,inlandCode,"+
"interLogistics,interCode,storage,interPDF,goodsInfo,billDate,state,payState,memo,dealMemo,billcode) VALUES(#{username}, #{electricity}, #{inlandLogistics}, #{inlandCode}, #{"+
"interLogistics}, #{interCode}, #{storage}, #{interPDF}, #{goodsInfo}, now(), #{state}, #{payState},#{memo},#{dealMemo},#{billcode})")
    int insert(SendApply sendApply);
	
	@Update("update sendapply set electricity=#{electricity},inlandLogistics=#{inlandLogistics},inlandCode=#{inlandCode},"+
	"interLogistics=#{interLogistics},interCode=#{interCode},storage=#{storage},interPDF=#{interPDF},goodsInfo=#{goodsInfo},memo=#{memo} where id=#{id}")
	int update(SendApply sendApply);
	
	@Delete("delete from sendapply where id=#{id}")
	int delete(@Param("id") int id);
	
	@Select("SELECT concat(substring(billcode,1,2),(substring(billcode,3,12)+1)) as billcode FROM `sendApply` " +
			"where DATE_FORMAT(billdate,'%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d') order by billcode desc limit 1")
	String selectBillCode();
	
	@Select("select inlandCode from sendApply where inlandCode=#{inlandCode} and ifnull(dealMemo,'')<>'异常' limit 1")
	String checkInlandCode(@Param("inlandCode") String inlandCode);
	
	@Select("select inlandCode from sendApply where inlandCode=#{inlandCode} and ifnull(dealMemo,'')<>'异常' and id<>#{id} limit 1")
	String checkInlandCodeById(@Param("inlandCode") String inlandCode,@Param("id") int id);
	
	@Select("select interCode from sendApply where interCode=#{interCode} and ifnull(dealMemo,'')<>'异常' limit 1")
	String checkInterCode(@Param("interCode") String interCode);
	
	@Select("select interCode from sendApply where interCode=#{interCode} and ifnull(dealMemo,'')<>'异常'  and id<>#{id} limit 1")
	String checkInterCodeById(@Param("interCode") String interCode,@Param("id") int id);
	
	@Select("SELECT a.id,a.username,b.`name`,b.number,c.electrify as electricity,a.electricity as electrifyid,a.interLogistics as interLogisticid,"+
			"a.inlandLogistics,a.inlandCode,d.interlogitsc as interLogistics,a.`storage`,a.interCode,a.interPDF,a.goodsinfo,"+
			"a.billdate,ifnull(a.state,'未处理') as state,ifnull(a.payState,'未支付') as payState,ifnull(a.memo,'') as memo,ifnull(a.dealMemo,'') as dealMemo,a.billcode "+
			"FROM `sendapply`  a left join users b on a.username=b.username left join electrifys "+
			" c on a.electricity=c.id left join interlogistscs d on a.interLogistics=d.id"+
			" where a.username=#{username} and a.inlandCode like #{inlandCode} order by a.billDate desc limit #{pageIndex},#{pageSize}")
	List<SendApplyQuery> getSendApplyQuery(@Param("username") String username,@Param("pageIndex") int pageIndex,
			@Param("pageSize") int pageSize,@Param("inlandCode") String inlandCode);
	
	@Select("SELECT a.id,a.username,b.`name`,b.number,b.school,c.electrify as electricity,"+
			"a.inlandLogistics,a.inlandCode,d.interLogitsc as interLogistics,a.`storage`,a.interCode,a.interPDF,a.goodsinfo,"+
			"a.billdate,ifnull(a.state,'未处理') as state,ifnull(a.payState,'未支付') as payState,ifnull(a.memo,'') as memo,ifnull(a.dealMemo,'') as dealMemo,a.billcode "+
			"FROM `sendapply`  a left join users b on a.username=b.username "+
			" left join electrifys c on a.electricity=c.id "+
			" left join interLogistscs d on a.interLogistics=d.id "+
			"where ifnull(a.dealmemo,'')<>'已代发' and a.inlandCode like #{inlandCode} and a.username "+
			" like #{username} and b.name like #{username} and b.school like #{school} order by a.billDate desc limit #{pageIndex},#{pageSize}")
	List<SendApplyQuery> getMainSendApplyQuery(@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize,
			@Param("inlandCode") String inlandCode,@Param("username") String username,@Param("school") String school);
	
	@Select("SELECT a.id,a.username,b.qq,b.`name`,b.number,c.electrify as electricity,"+
			"a.inlandLogistics,a.inlandCode,d.interLogitsc as interLogistics,a.`storage`,a.interCode,a.interPDF,a.goodsinfo,"+
			"a.billdate,ifnull(a.state,'未处理') as state,ifnull(a.payState,'未支付') as payState,ifnull(a.memo,'') as memo,ifnull(a.dealMemo,'') as dealMemo,a.billcode "+
			"FROM `sendapply`  a left join users b on a.username=b.username left join electrifys c on a.electricity=c.id "+
			" left join interLogistscs d on a.interLogistics=d.id where a.inlandCode=#{inlandCode}  limit 1 ")
	SendApplyQuery getSendApplyByInlandCode(@Param("inlandCode") String inlandCode);
	
	@Update("update sendapply set dealmemo=#{dealmemo},state='异常' where id=#{id}")
	int updateDealMemo(@Param("dealmemo") String dealmemo,@Param("id") int id);
	
	@Update("update sendapply set state='已代发' where id=#{id}")
	int updateState(@Param("id") int id);
	
	@Select("select state from sendapply where billcode=#{billcode}")
	String selectStateByBillCode(String billcode);
	
	@Update("update sendapply set payState='已支付' where billcode=#{billcode}")
	int updatePayState(@Param("billcode") String billcode);
	
	@Select("select count(*) as count from sendapply where username=#{username} and inlandCode like #{inlandCode}")
	int getCount(@Param("username") String username,@Param("inlandCode") String inlandCode);
	
	@Select("select count(*) as count from sendapply  a left join users b on a.username=b.username  where ifnull(dealmemo,'')<>'已代发' and a.inlandCode like #{inlandCode} and a.username "+
			" like #{username} and b.name like #{username} and b.school like #{school}")
	int getMainCount(@Param("inlandCode") String inlandCode,@Param("username") String username,@Param("school") String school);
	
	@Select("select interPDF from sendapply where id = #{id}")
	String getFilenameById(@Param("id") int id);
}
