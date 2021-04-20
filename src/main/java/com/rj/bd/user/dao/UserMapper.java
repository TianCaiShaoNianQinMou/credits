package com.rj.bd.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.user.entity.Img;
import com.rj.bd.user.entity.User;

public interface UserMapper extends BaseMapper<User>{
	@Select("SELECT * FROM USER WHERE username=#{username}")
	User findUserByname(String username);
	/**
	 * @desc
	 * @param img
	 * @return
	 */
	@Insert("INSERT INTO img VALUES (#{id},#{name},#{url})")
	int addImg(Img img);
	
	/**
	 * @decs 查询图片
	 * @return
	 */
	@Select("select * from img")
	List<Map<String, Object>> queryImg();

}
