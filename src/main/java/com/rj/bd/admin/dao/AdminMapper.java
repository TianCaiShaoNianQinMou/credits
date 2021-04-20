package com.rj.bd.admin.dao;

/**
 * @decs adminmapper
 */
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.admin.entity.Admin;
import com.rj.bd.admin.entity.JiLu;
import com.rj.bd.covrersion.entity.Coversion;
import com.rj.bd.shop.entity.Shop;
import com.rj.bd.user.entity.User;

public interface AdminMapper {
	
	/**
	 * @desc admin的登录
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM admin WHERE username=#{username}")
	Admin selectByAdmin(String username);

	/**
	 * @desc 查询所有用户
	 * @return
	 */
	@Select("select * from user")
	List<User> findByUser();

	/**
	 * @desc 管理员查询兑换记录
	 * @return
	 */
	@Select("select * from shop")
	List<Shop> findByShop();

	/**
	 * @desc 管理员查询商品表
	 * @return
	 */
	@Select("SELECT * FROM conversion")
	List<Coversion> findCoversion();

	/***
	 * @desc 添加学生
	 * @param userId
	 * @param username
	 * @param password
	 * @param name
	 * @param sex
	 * @param tel
	 * @param money
	 * @param token
	 * @return
	 */
	@Insert("insert into user values(#{userId},#{username},#{password},#{name},#{sex},#{tel},#{money},#{token})")
	int addUser(@Param("userId") String userId, @Param("username") String username, @Param("password") String password,
			@Param("name") String name, @Param("sex") String sex, @Param("tel") String tel,
			@Param("money") String money, @Param("token") String token);

	/**
	 * @desc 查询记录
	 * @return
	 */
	@Select("SELECT user.username AS username,conversion.conName AS conname,shop.shopId AS shopid,shop.shopTime AS shoptime,shop.address AS shopaddress FROM shop,USER,conversion WHERE  shop.userId=user.userId AND shop.conId=conversion.conId")
	List<JiLu> queryShopJilu();

	/**
	 * @desc 修改用户积分
	 * @param money
	 * @return
	 */
	@Update("update user set money=#{money} where userId=#{userId}")
	int updatemoeny(@Param("money") String money, @Param("userId") String userId);

	/**
	 * @desc 管理员导出excel查询记录表
	 * @return
	 */
	@Select("SELECT user.username AS username,conversion.conName AS conname,shop.shopId AS shopid,shop.shopTime AS shoptime,shop.address AS shopaddress FROM shop,USER,conversion WHERE  shop.userId=user.userId AND shop.conId=conversion.conId")
	List<JiLu> allJiLu();

	/**
	 * @decs 管理员导出excle查询用户表
	 */
	@Select("select * from user")
	List<User> allUser();

	/**
	 * @desc 管理员添加商品
	 * @param conId
	 * @param conMoney
	 * @param conMoney2
	 * @param conDelete
	 * @return
	 */
	@Insert("insert into conversion values(#{conId},#{conName},#{conMoney},#{conDelete})")
	int insertConversion(@Param("conId") String conId, @Param("conName") String conName,@Param("conMoney") String conMoney,
			 @Param("conDelete") String conDelete);

	/**
	 * @desc 管理员修改conDelete 是否展示
	 * @param conId
	 * @param conDelete
	 * @return
	 */
	@Update("update conversion set conDelete=#{conDelete} where conId=#{conId}")
	int updateConversion(@Param("conId") String conId, @Param("conDelete") String conDelete);

	/**
	 * @desc 管理员修改conDelete 是否展示
	 * @param conId
	 * @param conDelete
	 * @return
	 */
	@Update("update conversion set conName=#{conName}, conMoney=${conMoney} ,conDelete=#{conDelete} where conId=#{conId}")
	int updateConversion(@Param("conId") String conId, @Param("conName") String conName,
			@Param("conMoney") String conMoney, @Param("conDelete") String conDelete);

	/**
	 * @desc e-charts图
	 * @return
	 */
	@Select("SELECT COUNT(*) as value ,sex as name FROM USER GROUP BY sex")
	List<Map<String, Integer>> SelectSex();
	/**
	 * @desc 模糊查询
	 * @param name
	 * @return
	 */
	@Select("select * from user where name like %#{name}%")
	List<User> LikeName(@Param("name") String name);

	/**
	 * @decs 模糊查询
	 * @param conName
	 * @return
	 */
	@Select("SELECT * FROM  conversion WHERE conName LIKE #{conName}")
	List<Coversion> LikeConName(String conName);
	
	/**
	 * @desc 分页
	 * @param firstShop
	 * @param larstShop
	 * @return
	 */
	@Select("SELECT * FROM shop LIMIT #{arg0},#{arg1}")
	List<Map<String, Object>> selectShopPage(int firstShop, int larstShop);

	/**
	 * @desc 分页栏
	 */
	@Select("SELECT COUNT(*) as sum, CEILING(COUNT(*)/#{size}) as yeshu FROM shop ")
	Map<String, Object> fenYeLan(Integer size);
	/**
	 * @desc 模糊查询用户姓名
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM USER WHERE NAME LIKE #{username} ")
	List<User> selectLikeUsername(@Param("username")String username);
}
