package com.rj.bd.covrersion.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.covrersion.entity.Coversion;
import com.rj.bd.user.entity.User;

public interface ICoversionService {
	/**
	 * @desc 用户查询商品
	 * @return
	 */
	Map<String, Object> queryConversion();
	/**
	 * @desc 用户兑换商品
	 * @param token
	 * @param conId
	 * @param address
	 * @return
	 */
	Map<String, Object> duihuanShangpin(String token,String conId,String address);
}
