package com.guiji.robot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guiji.robot.exception.RobotException;
import com.guiji.utils.StrUtils;

/**
 * @ClassName: ReadTxtUtil
 * @Description: 文本读取工具类
 * @date 2018年11月20日 下午4:04:24
 * @version V1.0
 */
public class ReadTxtUtil {
	private static final Logger logger = LoggerFactory.getLogger(ReadTxtUtil.class);

	
	/**
	 * 读取文本
	 * @param filePath
	 * @return
	 */
	public static String readTxtFile(String filePath) {
		if (StrUtils.isEmpty(filePath)) {
			return null;
		}
		InputStream is = null;
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				is = new FileInputStream(file);
				read = new InputStreamReader(is, encoding);// 考虑到编码格式
				bufferedReader = new BufferedReader(read);
				StringBuilder sb = new StringBuilder();
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					sb.append(lineTxt);
				}
				read.close();
				return sb.toString();
			} else {
				throw new RobotException("文件"+filePath+"不存在！");
			}
		} catch (Exception e) {
			logger.error("读取文件"+filePath+"内容出错",e);
		}finally {
			if(bufferedReader!=null)
				try {if(bufferedReader!=null) {bufferedReader.close();}} catch (IOException e) {e.printStackTrace();}
				try {if(read!=null) {read.close();}} catch (IOException e) {e.printStackTrace();}
				try {if(is!=null) {is.close();}} catch (IOException e) {e.printStackTrace();}
		}
		return null;
	}
}
