package com.jishengsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jishengsoft.pojo.User;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM users")
    List<User> getAll();
	
	@Select("SELECT  * FROM users WHERE username = #{username} limit 1")
    User findByName(@Param("username") String username);
	
	@Select("SELECT  * FROM users WHERE number = #{number} limit 1")
    User findByNumber(@Param("number") String number);
	
	@Select("select * from users where number = #{number} and username=#{username}")
	User findByNumberAndUser(@Param("number") String number,@Param("username") String username);
	
	@Select("SELECT  * FROM users WHERE username = #{username} and password = #{password} limit 1")
    User login(@Param("username") String username,@Param("password") String password);
	
	@Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") long id);
	
	@Select("SELECT * FROM users WHERE username = #{username} limit 1")
    User findByUsername(@Param("username") String username);
	
	@Update("UPDATE users SET name=#{name},age=#{age} WHERE id =#{id}")
    void update(User user);
	
	@Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);
	
    @Insert("INSERT INTO users(username,password,number,qq,student,school,NAME) "+
    "VALUES(#{username},#{password},#{number},#{qq},#{student},#{school},#{name})")
    int insert(User user);
    
    @Update("update users set password=#{password} where username=#{username}")
    int updatePass(@Param("username") String username,@Param("password") String password);
    
    @Update("update users set name=#{name},qq=#{qq},student=#{student},school=#{school},number=#{number} where username=#{username}")
    int updateInformation(User user);
}
