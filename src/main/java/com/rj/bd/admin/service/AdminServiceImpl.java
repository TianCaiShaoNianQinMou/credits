package com.rj.bd.admin.service;

/**
 * @desc admin接口
 */
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rj.bd.admin.dao.AdminMapper;
import com.rj.bd.admin.entity.Admin;
import com.rj.bd.admin.entity.JiLu;
import com.rj.bd.covrersion.entity.Coversion;
import com.rj.bd.shop.entity.Shop;
import com.rj.bd.user.entity.User;
import com.rj.bd.utils.SnowflakeUtils;

import cn.hutool.core.math.Money;
import cn.hutool.core.util.IdUtil;

@Transactional
@Service("adminService")
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private AdminMapper adminMapper;

	/**
	 * @desc 管理员模块的登录和验证
	 */
	public Map<String, Object> login(String name, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		Admin admin = adminMapper.selectByAdmin(name);
		if (admin == null) {
			map.put("code", "-1");
			map.put("msg", "请先去注册");
			return map;
		} else {
			String password1 = admin.getPassword();
			if (password.equals(password1)) {
				map.put("code", "200");
				map.put("msg", "登录成功");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("token", admin.getToken());
				map.put("data", data);
				return map;
			} else {
				map.put("code", "-1");
				map.put("msg", "密码错误");
				return map;
			}
		}
	}

	/**
	 * @desc 查询所有用户
	 */
	public Map<String, Object> queryUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> list = adminMapper.findByUser();
		for (User user : list) {
			user.setCaozuo("<a class='btn btn-secondary' data-id=" + user.getUserId() + " href=money.html?userId="
					+ user.getUserId() + ">修改</a>");
		}
		map.put("code", "200");
		map.put("msg", "查询成功");
		map.put("data", list);
		return map;
	}

	/**
	 * @desc 查询商品记录表
	 */
	public Map<String, Object> queryShop() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Shop> list = adminMapper.findByShop();
		map.put("code", "200");
		map.put("msg", "查询成功");
		map.put("data", list);
		return map;
	}

	/**
	 * @desc 查询商品
	 */
	public Map<String, Object> queryConversion() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Coversion> list = adminMapper.findCoversion();
		for (Coversion coversion : list) {
			coversion.setCaozuo("<a class='btn btn-secondary' data-id=" + coversion.getConId()
					+ " href=billUpdate.html?conId=" + coversion.getConId() + ">修改</a>");
		}
		map.put("code", "200");
		map.put("msg", "查询成功");
		map.put("data", list);
		System.out.println(list);
		return map;
	}

	/**
	 * @desc 添加用户
	 */
	public Map<String, Object> addUser(String username, String password, String name, String sex, String tel,
			String money) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = IdUtil.simpleUUID();
		String token = IdUtil.simpleUUID();
		int adduser = adminMapper.addUser(userId, username, password, name, sex, tel, money, token);
		map.put("code", "200");
		map.put("msg", "添加成功");
		return map;
	}

	/**
	 * @desc 查询购买记录
	 */
	public Map<String, Object> queryShopJilu() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<JiLu> list = adminMapper.queryShopJilu();
		map.put("code", "200");
		map.put("msg", "查询成功");
		map.put("data", list);
		System.out.println(list);
		return map;
	}

	/**
	 * @desc 修改用户积分
	 */
	public Map<String, Object> updateUserMoney(String money, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int update = adminMapper.updatemoeny(money, userId);
		map.put("coed", "200");
		map.put("msg", "修改成功");
		return map;
	}

	/**
	 * @desc 导出兑换所有记录
	 */
	public List<JiLu> allJiLu() {
		return adminMapper.allJiLu();
	}

	/**
	 * @desc 导出所有用户
	 */
	public List<User> allUser() {
		return adminMapper.allUser();
	}

	/**
	 * @desc添加 兑换商品
	 */
	public Map<String, Object> insertConversion(String conId, String conName, String conMoney, String conDelete) {
		Map<String, Object> map = new HashMap<String, Object>();
		conId = SnowflakeUtils.getSnowflake() + "";
		int insert = adminMapper.insertConversion(conId, conName, conMoney,  conDelete);
		map.put("code", "200");
		map.put("msg", "添加成功");
		return map;
	}

	/**
	 * @desc 更新商品
	 */
	public Map<String, Object> updateConversion(String conId, String conName, String conMoney, String conDelete) {
		Map<String, Object> map = new HashMap<String, Object>();
		Coversion coversion = new Coversion();
		
		/**
		 * @decs 更新商品状态
		 */
		int update = adminMapper.updateConversion(conId, conName, conMoney, conDelete);
		map.put("coed", "200");
		map.put("msg", "修改成功");
		return map;
	}

	/**
	 * @desc 查询性别
	 */
	public Map<String, Object> selectSex() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Integer>> data = adminMapper.SelectSex();
		System.out.println(data);
		map.put("code", "200");
		map.put("msg", "查询成功");
		map.put("data", data);
		return map;
	}

	/**
	 * @desc 模糊查询姓名
	 */
	public Map<String, Object> selectLikeName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> data = adminMapper.LikeName(name);
		map.put("code", "200");
		map.put("msg", "模糊查询成功");
		map.put("data", data);
		return map;
	}

	/**
	 * @desc 模糊查询商品名称
	 */
	public Map<String, Object> selectLikeConversion(String conName) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Coversion> data = adminMapper.LikeConName("%" + conName + "%");
		System.out.println(data);
		map.put("code", "200");
		map.put("msg", "模糊查询");
		map.put("data", data);
		System.out.println(map);
		return map;
	}

	final Integer SIZE = 5;

	/**
	 * @desc 分页
	 */
	public Map<String, Object> fanHui(String page) {
		int ShopPasge = Integer.parseInt(page);
		int firstShop = (ShopPasge - 1) * SIZE;
		int larstShop = ShopPasge * SIZE;
		List<Map<String, Object>> map = adminMapper.selectShopPage(firstShop, larstShop);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", "200");
		data.put("msg", "分页成功");
		data.put("data", map);
		return data;
	}

	/**
	 * @decs 分页2
	 */
	public Map<String, Object> page() {
		Map<String, Object> map = adminMapper.fenYeLan(SIZE);
		return prinf(200, "分页成功", map);
	}

	public static Map<String, Object> prinf(Integer code, String msg, Object data) {
		HashMap<String, Object> myData = new HashMap<String, Object>();
		myData.put("code", code);
		myData.put("msg", msg);
		myData.put("data", data);
		return myData;
	}

	public Map<String, Object> selectLikeUsername(String username) {
		System.out.println("adminServiceImpl---->selectLikeUsername");
		Map<String, Object> map=new HashMap<String, Object>();
		List<User> data=adminMapper.selectLikeUsername("%"+ username+ "%");
		map.put("code", "200");
		map.put("msg", "查询成功");
		map.put("data", data);
		return map;
	}
}
