package com.rj.bd.admin.controller;

/**
 * @desc admin的c层
 * @author 天才选手
 * @time 2021年4月19日
 */
import java.util.HashMap;
/**
 * @desc 管理员模块
 */
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rj.bd.admin.entity.Admin;
import com.rj.bd.admin.service.IAdminService;
import com.rj.bd.user.entity.User;

@ResponseBody
@Controller
@RequestMapping("/admin")
public class AdminConrtoller {
	@Autowired
	private IAdminService adminService;
	// 因为一会要清空session，所以做成全局的
	HttpSession session;

	/**
	 * @desc 管理员登录
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public Map<String, Object> login(String name, String password) {
		System.out.println("admin-------->login");
		Map<String, Object> map = adminService.login(name, password);
		return map;
	}

	/**
	 * @desc 管理员查询所有用户
	 * @return
	 */
	@RequestMapping("/queryUser")
	public Map<String, Object> queryUser() {
		System.out.println("admin--------->queryUser");
		Map<String, Object> map = adminService.queryUser();
		return map;
	}

	/**
	 * @desc 管理员查询Shop
	 * @return
	 */
	@RequestMapping("/queryShop")
	public Map<String, Object> queryShop() {
		System.out.println("admin--------->queryshop");
		Map<String, Object> map = adminService.queryShop();
		return map;
	}

	/**
	 * @desc 查询Coversion
	 * @return
	 */
	@RequestMapping("/queryCoversion")
	public Map<String, Object> queryCoversion() {
		System.out.println("admin--------->Coversion");
		Map<String, Object> map = adminService.queryConversion();
		return map;
	}

	/**
	 * @desc 添加用户
	 */
	@RequestMapping("/addUser")
	public Map<String, Object> addUser(String username, String password, String name, String sex, String tel,
			String money) {
		System.out.println("admin---------->addUser");
		Map<String, Object> map = adminService.addUser(username, password, name, sex, tel, money);
		return map;
	}

	/**
	 * @desc 管理员查询商品记录
	 */
	@RequestMapping("/queryShopJilu")
	public Map<String, Object> queryShopJilu() {
		System.out.println("admin---------->queryShopJilu");
		Map<String, Object> map = adminService.queryShopJilu();
		return map;
	}

	/**
	 * @desc 管理员修改用户积分
	 */
	@RequestMapping("/updateUserMoney")
	public Map<String, Object> updateUserMoney(String money, String userId) {
		System.out.println("admin---------->updateUserMoney");
		Map<String, Object> map = adminService.updateUserMoney(money, userId);
		return map;
	}

	/**
	 * @dess 管理员添加商品
	 */
	@RequestMapping("/insertConversion")
	public Map<String, Object> insertConversion(String conId, String conName, String conMoney, String conDelete) {
		System.out.println("admin---->insertConversion");
		Map<String, Object> map = adminService.insertConversion(conId, conName, conMoney, conDelete);
		return map;
	}

	/**
	 * @desc 管理员修改conDelete 是否展示
	 * @param conId
	 * @param conDelete
	 * @return
	 */
	@RequestMapping("/updateconversion")
	public Map<String, Object> updateconversion(String conId, String conName, String conMoney, String conDelete) {
		System.out.println("admin--------------updateconversion");
		Map<String, Object> map = adminService.updateConversion(conId, conName, conMoney, conDelete);
		return map;
	}

	/**
	 * @desc e-charts
	 * @param sex
	 * @return
	 */
	@RequestMapping("/ecUser")
	public Map<String, Object> EcUser() {
		System.out.println("ecUser");
		return adminService.selectSex();
	}

	/**
	 * @desc 模糊查询
	 */
	@RequestMapping("/LikeUser")
	public Map<String, Object> LikeUser(String name) {
		System.out.println(name);
		return adminService.selectLikeName(name);
	}

	/**
	 * @desc 模糊查询
	 */
	@RequestMapping("/LikeCon")
	public Map<String, Object> LikeCon(String conName) {
		System.out.println("LikeCon");
		System.out.println(conName);
		Map<String, Object> map = adminService.selectLikeConversion(conName);
		map.put("code", "200");
		map.put("msg", "模糊查询成功");
		return map;
	}

	/**
	 * @desc 分页
	 * @param page
	 * @return
	 */
	@RequestMapping("/fenye")
	public Map<String, Object> FenYe(String page) {
		System.out.println(page);
		return adminService.fanHui(page);
	}

	/**
	 * @desc 分页2
	 * @return
	 */
	@RequestMapping("/fenye2")
	public Map<String, Object> FenYe2() {
		return adminService.page();
	}

	/**
	 * @desc 模糊查询用户
	 */
	@RequestMapping("/selectLikeUsername")
	public Map<String, Object> selectLikeUsername(String username){
		System.out.println("andmin---seleceLikeUsername");
		System.out.println(username);
		Map<String, Object> map=adminService.selectLikeUsername(username);
		return map;
		}

}
