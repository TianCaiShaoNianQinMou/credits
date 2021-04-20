package com.rj.bd.admin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rj.bd.admin.entity.Admin;
import com.rj.bd.admin.entity.JiLu;
import com.rj.bd.user.entity.User;import cn.hutool.db.sql.Query;

public interface IAdminService {
	
	/**
	 * @desc 管理员模块的登录
	 * @param name
	 * @param password
	 * @return
	 */
	Map<String, Object> login(String name,String password);
	
	/**
	 * @desc 管理员查询所有用户
	 * @return
	 */
	Map<String, Object> queryUser();

	/**
	 * @desc 管理员查询兑换商品记录表
	 */
	Map<String, Object> queryShop();
	/**
	 * @desc 管理员查询商品表
	 * @return
	 */
	Map<String, Object> queryConversion();
	/**
	 * @desc 添加用户
	 * @param user
	 * @return
	 */
	Map<String, Object> addUser(String username, String password, String name, String sex, String tel,
			String money);

	/**
	 * @desc 管理⚪查询shop表
	 */
	Map<String, Object> queryShopJilu();
	/**
	 * @desc 修改用户积分
	 * @return
	 */
	Map<String, Object> updateUserMoney(String money,String userId);
	/**
	 * @desc shop导出excel查询所有记录表
	 * @return
	 */
	List<JiLu> allJiLu();
	/**
	 * user导出excel查询所有用户
	 * @return
	 */
	List<User> allUser();
	/**
	 * @desc 管理员添加商品
	 * @param conId
	 * @param conMoney
	 * @param conName
	 * @param conDelete
	 * @return
	 */
	Map<String, Object> insertConversion(String conId,String conName,String conMoney,String conDelete);
	
	/**
	 * @desc 管理员修改conDelete
	 * @param conId
	 * @param conDelete
	 * @return
	 */
	Map<String, Object> updateConversion(String conId, String conName, String conMoney, String conDelete);
	/**
	 * @desc E-chatrs
	 * @return
	 */
	Map<String, Object> selectSex();
	
	/**
	 * @desc 条件查询
	 */
	Map<String, Object> selectLikeName(String name);
	
	/**
	 * @throws UnsupportedEncodingException 
	 * @decs 模糊查询
	 */
	Map<String, Object> selectLikeConversion(String conName) ;
	
	
	/**
	 * @desc 查询兑换记录分页
	 */
	Map<String, Object> fanHui(String page);
	
	
	/**
	 * @desc page
	 */
	Map<String, Object> page();
	/**
	 * @desc 模糊查询用户姓名
	 * @param username
	 * @return
	 */
	Map<String, Object> selectLikeUsername(String username);
}

