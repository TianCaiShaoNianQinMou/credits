package com.rj.bd.utils;
/**
 * @dscs 雪花算法
 */
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class SnowflakeUtils {
	
	//定义的 
	private static final  Snowflake SONWFLAKE = IdUtil.getSnowflake(1, 1);
	
	// 获取下一个 
	public static Long getSnowflake(){
		return SONWFLAKE.nextId();
	}
	

	
}
