package com.rj.bd.covrersion.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.covrersion.entity.Coversion;

public interface CoversionMapper extends BaseMapper<Coversion>{
	/**
	 * @desc 查询全部商品
	 * @return
	 */
	@Select("select * from conversion where conDelete='1'")
	List<Coversion> findAll();
	/**
	 * desc 根据token查询用户
	 * @param token
	 * @return
	 */
	@Select("select * from user where token=#{token}")
	Map<String, Object> selectToken(String token);
	/**
	 * @desc 根据conId查询商品
	 * @param conId
	 * @return
	 */
	@Select("select * from conversion where conId=#{conId}")
	Coversion selectCon(String conId);
	/**
	 * @desc 根据userId修改 money
	 * @param money
	 * @param userId
	 * @return
	 */
	@Update("UPDATE  USER SET money=#{money} WHERE userID=#{userId}")
	int UpdateUser(@Param("money")String money,@Param("userId")String userId);
	/**
	 * @desc 兑换记录
	 * @param shopId
	 * @param userId
	 * @param conId
	 * @param shopTime
	 * @param address
	 * @return
	 */
	@Insert("insert into shop values(#{shopId},#{userId},#{conId},#{shopTime},#{address})")
	int insertShop(@Param("shopId")String shopId,@Param("userId")String userId,@Param("conId")String conId,@Param("shopTime")String shopTime,@Param("address")String address);
}
