package com.rj.bd.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.rj.bd.user.entity.Img;
import com.rj.bd.user.service.IUserService;
import com.rj.bd.utils.SnowflakeUtils;
import com.rj.bd.utils.UploadUtill;

import cn.hutool.core.lang.UUID;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

	@Autowired
	private IUserService userService;
	/**
	 * @desc 用户登录
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public Map<String, Object> login(String name, String password) {
		System.out.println("login");
		Map<String, Object> map = userService.login(name, password);
		return map;
	}
	
	/**
	 * @desc 查询图片
	 * @return
	 */
	@RequestMapping("/queryImg")
	public Map<String, Object> queryImg() {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("img");
		List<Map<String, Object>> map1 = userService.queryImg();
		map.put("code", "200");
		map.put("msg", "查询成功");
		map.put("data", map1);
		return map;
	}
	/**
	 * @desc 添加图片
	 * @param pictureFile
	 * @param request
	 * @param pOwner
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("insertPicture")
	public Map<String, Object> insertPicture(@RequestParam(value="file")MultipartFile pictureFile,HttpServletRequest request, String pOwner) throws IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		System.out.println("pOwner:" + pOwner);
		// pictureURL是数据库里picture_url的值，这里用到一个封装的工具类UploadUtil
		String url= UploadUtill.imageUpload(pictureFile, request);
		System.out.println("pictureURL:" + url);
        //获取上传时的文件名
        String name = FilenameUtils.getName(pictureFile.getOriginalFilename());
        System.out.println("pictureName:" + name);
        // 把图片数据保存到数据库
        Img img=new Img();
        img.setName(name);
        img.setUrl(url);
       
        int insert = userService.insert(img);
        if (insert > 0 && url != "") {
        	return map;
		}
        return map;
	}
	
}
