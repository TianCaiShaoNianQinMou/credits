package com.rj.bd.utils;
/**
 * 下载工具类
 */
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtill {
	public static String imageUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) {
		String path ="D:\\HBuilderProjects\\shops\\imgs";
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		String filename = UUID.randomUUID().toString().replaceAll("-", "") + "." + ext;
		// 写入文件成功之后，返回的数据，也就是数据库里要存的文件的url
		String src =filename;
		File targetFile = new File(path,filename);
		try {
			if (!targetFile.exists()) {
				// 写入文件
				file.transferTo(targetFile);
			}
			return src;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 写入文件失败，则返回空字符串
		return "";
	}
}
