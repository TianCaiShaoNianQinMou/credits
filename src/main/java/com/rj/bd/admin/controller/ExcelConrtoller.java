package com.rj.bd.admin.controller;
/**
 * @desc excel导入导出
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.WriterOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rj.bd.admin.entity.JiLu;
import com.rj.bd.admin.service.IAdminService;
import com.rj.bd.user.entity.User;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

@ResponseBody
@Controller
@RequestMapping("/excel")
public class ExcelConrtoller {

	@Autowired
	private IAdminService adminService;
	/**
	 * @desc 导出兑换记录的excel
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/expotShopExcel")
	public Map<String, Object> exportShopExcel(HttpServletResponse response) throws UnsupportedEncodingException {
		// 固定写法 复制就行 这里写导出excel的名字 设置下载在浏览器端，等用户下载
		String fileName = "shopJilu.xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "utf-8"));// 设置文件头编码格式
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
		response.setHeader("Cache-Control", "no-cache");// 设置头
		response.setDateHeader("Expires", 0);// 设置日期头
		// 获取数据库查询的所有数据
		List<JiLu> list = adminService.allJiLu();
		// 将查询结果带到页面 回显函数使用
		Map<String, Object> map = new HashMap();
		// 创建导出表格的对象
		Workbook wb = new HSSFWorkbook();
		// 创建表
		Sheet sheet = wb.createSheet("sheet1");
		// 获取表的第一行元素，也就是0行
		Row row = sheet.createRow(0);
		// 创建存放列的数组
		Cell[] cell = new HSSFCell[6];
		for (int i = 0; i < cell.length; i++) {
			// 吧每一列放到数组中
			cell[i] = row.createCell(i);
		}
		// 这个是写的标题头
		// 给第0行第一列元素赋值
		cell[0].setCellValue("用户账号");
		// 给第0行第二列元素赋值
		cell[1].setCellValue("商品名称");
		// 给第0行第三列元素赋值
		cell[2].setCellValue("购买记录id");
		// 给第0行第四列元素赋值
		cell[3].setCellValue("购买时间");
		// 给第0行第五列元素赋值
		cell[4].setCellValue("收货地址");
		try {
			// 循环获取从数据库中的集合每个pojo对象的数据
			for (int i = 0; i < list.size(); i++) {
				// 查询的每个对象的数据
				JiLu jiLu = list.get(i);
				// 设置要插入的行为i+1(就是标题下的第一行)
				Row row1 = sheet.createRow(i + 1);
				// 创建存放列的数组
				Cell[] cell2 = new HSSFCell[6];
				for (int j = 0; j < cell.length; j++) {
					// 吧每一列放到数组中
					cell2[j] = row1.createCell(j);
				}
				cell2[0].setCellValue(i + 1);
				cell2[1].setCellValue(jiLu.getUsername());
				cell2[2].setCellValue(jiLu.getConname());
				cell2[3].setCellValue(jiLu.getShopid());
				cell2[4].setCellValue(jiLu.getShoptime());
				cell2[5].setCellValue(jiLu.getShopaddress());
			}
			// 输出到下载人的电脑上
			wb.write(response.getOutputStream());
			// 刷新
			response.getOutputStream().flush();
			// 关闭
			response.getOutputStream().close();
		} catch (IOException e) {

		} finally {
			try {
				if (wb != null) {
					// 关闭流
					wb.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * @desc 导出user的excel
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/expotUserExcel")
	public Map<String, Object> exportUserExcel(HttpServletResponse response) throws UnsupportedEncodingException {
		// 固定写法 复制就行 这里写导出excel的名字 设置下载在浏览器端，等用户下载
		String fileName = "user.xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "utf-8"));// 设置文件头编码格式
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
		response.setHeader("Cache-Control", "no-cache");// 设置头
		response.setDateHeader("Expires", 0);// 设置日期头
		// 获取数据库查询的所有数据
		List<User> list = adminService.allUser();
		// 将查询结果带到页面 回显函数使用
		Map<String, Object> map = new HashMap();
		// 创建导出表格的对象
		Workbook wb = new HSSFWorkbook();
		// 创建表
		Sheet sheet = wb.createSheet("sheet1");
		// 获取表的第一行元素，也就是0行
		Row row = sheet.createRow(0);
		// 创建存放列的数组
		Cell[] cell = new HSSFCell[9];
		for (int i = 0; i < cell.length; i++) {
			// 吧每一列放到数组中
			cell[i] = row.createCell(i);
		}
		// 这个是写的标题头
		cell[0].setCellValue("用户id");
		cell[1].setCellValue("用户账号");
		cell[2].setCellValue("用户密码");
		cell[3].setCellValue("姓名");
		cell[5].setCellValue("性别");
		cell[6].setCellValue("电话");
		cell[7].setCellValue("剩余积分");
		cell[8].setCellValue("token值");
		try {
			// 循环获取从数据库中的集合每个pojo对象的数据
			for (int i = 0; i < list.size(); i++) {
				// 查询的每个对象的数据
				User user = list.get(i);
				// 设置要插入的行为i+1(就是标题下的第一行)
				Row row1 = sheet.createRow(i + 1);
				// 创建存放列的数组
				Cell[] cell2 = new HSSFCell[9];
				for (int j = 0; j < cell.length; j++) {
					// 吧每一列放到数组中
					cell2[j] = row1.createCell(j);
				}
				cell2[0].setCellValue(i + 1);
				cell2[1].setCellValue(user.getUserId());
				cell2[2].setCellValue(user.getUsername());
				cell2[3].setCellValue(user.getPassword());
				cell2[4].setCellValue(user.getName());
				cell2[5].setCellValue(user.getSex());
				cell2[6].setCellValue(user.getTel());
				cell2[7].setCellValue(user.getMoney());
				cell2[8].setCellValue(user.getToken());
			}
			// 输出到下载人的电脑上
			wb.write(response.getOutputStream());
			// 刷新
			response.getOutputStream().flush();
			// 关闭
			response.getOutputStream().close();
		} catch (IOException e) {

		} finally {
			try {
				if (wb != null) {
					// 关闭流
					wb.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
