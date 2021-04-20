package com.rj.bd.covrersion.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.covrersion.dao.CoversionMapper;
import com.rj.bd.covrersion.entity.Coversion;
import com.rj.bd.utils.SnowflakeUtils;
import com.rj.bd.utils.Time;

import cn.hutool.core.util.IdUtil;

@Transactional
@Service("coversionService")
public class CoversionServiceImpl implements ICoversionService {
	@Autowired
	private CoversionMapper coversionMapper;
	/**
	 * @desc 查询所有商品
	 */
	public Map<String, Object> queryConversion() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Coversion> list = coversionMapper.findAll();
		for (Coversion coversion : list) {
			coversion.setCaozuo("<button class='btn btn-secondary' data-id=" + coversion.getConId() + " onclick='dohuihuan(this)'>兑换</button>");
		}
		map.put("data", list);
		map.put("code", "200");
		map.put("msg", "查询成功");
		return map;
	}
	/**
	 * @desc 兑换商品
	 */
	@RequestMapping("/duihuan")
	public Map<String, Object> duihuanShangpin(String token, String conId, String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Map<String, Object> userMap = coversionMapper.selectToken(token);
		if (userMap != null) {// 判断用户有没有登录
			// 登录以后获取用户id
			String userid = userMap.get("userId").toString();
			Coversion coversion = coversionMapper.selectCon(conId);
			if (coversion == null) {
				map.put("code", "-5");
				map.put("msg", "非法调用");
				return map;
			} else {
				String coversionMoney = coversion.getConMoney();
				String userMoney = userMap.get("money").toString();
				int covMoney = Integer.parseInt(coversionMoney);
				int useMoney = Integer.parseInt(userMoney);
				if (useMoney - covMoney < 0) {// 判断用户积分够不够
					map.put("code", "-1");
					map.put("msg", "积分不足");
					return map;
				} else {
					int m = useMoney - covMoney;
					int money = coversionMapper.UpdateUser(m + "", userid);
					if (address == "") {//如果地址等于空的话让用户填写地址
						map.put("code", "-1");
						map.put("msg", "请填写地址");
						return map;
					} else {
						String timeTemp=System.currentTimeMillis()+"";
						String time=Time.stampToDate(timeTemp);
						int shop = coversionMapper.insertShop(SnowflakeUtils.getSnowflake()+"", userid, conId,
								time, address);// 兑换成功
						map.put("code", "200");
						map.put("msg", "兑换成功");
						return map;
					}

				}
			}
		} else {
			// 没登录的话提示请先登录
			map.put("code", "-1");
			map.put("msg", "请先进行登录");
			return map;
		}
	}

}
