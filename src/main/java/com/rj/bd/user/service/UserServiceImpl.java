package com.rj.bd.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.user.dao.UserMapper;
import com.rj.bd.user.entity.Img;
import com.rj.bd.user.entity.User;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * @decs 用户登录
	 */
	public Map<String, Object> login(String name, String password) {
		Map<String, Object> map=new HashMap<String, Object>();
		User user = userMapper.findUserByname(name);
		if (user==null) { 
			map.put("code", "-1");
			map.put("msg", "用户名不存在");
			return map;
		}else {
			String password1 = user.getPassword();
			if (password1.equals(password)) {
				map.put("code", "200");
				map.put("msg", "登陆成功");
				Map<String, Object> data=new HashMap<String, Object>();
				data.put("token", user.getToken());
				map.put("data", data);
				return map;
			}else {
				map.put("code", "-1");
				map.put("msg", "密码错误");
				return map;
			}
		}
	}

	public int insert(Img img) {
		// TODO Auto-generated method stub
		return userMapper.addImg(img);
	}

	public List<Map<String, Object>> queryImg() {
		// TODO Auto-generated method stub
		return userMapper.queryImg();
	}

	


}
