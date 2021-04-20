package com.rj.bd.covrersion.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rj.bd.covrersion.entity.Coversion;
import com.rj.bd.covrersion.service.ICoversionService;
@ResponseBody
@Controller
@RequestMapping("/coversion")
public class CoversionController {
	@Autowired
	private ICoversionService coversionService;
	/**
	 * @desc 查询所有商品
	 * @return
	 */
	@RequestMapping("/query")
	public Map<String, Object> findCoversion() {
		System.out.println("findCoversion");
		Map<String, Object> map = coversionService.queryConversion();
		return map;
	}
	
	/**
	 * @desc 用户兑换商品
	 * @param token
	 * @param conId
	 * @param address
	 * @return
	 */
	@RequestMapping("/duihuan")
	public Map<String, Object> duihuan(String token,String conId,String address){
		System.out.println("coversion---------->duihuan");
		Map<String, Object> map=coversionService.duihuanShangpin(token, conId,address);
		return map;
	}
}
