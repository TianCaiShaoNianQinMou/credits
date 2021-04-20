package com.rj.bd.user.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.user.entity.Img;


public interface IUserService {
	/**
	 * @desc 用户登录
	 * @param name
	 * @param password
	 * @return
	 */
	Map<String, Object> login(String name,String password);
	/**
	 * @desc 添加图片
	 * @param img
	 * @return
	 */
	int insert(Img img);
	/**
	 * @desc 查询全部图片
	 * @return
	 */
	List<Map<String, Object>> queryImg();

}
