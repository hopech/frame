package com.sicilon.frame.sutils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * @author 张博
 * @date 2017年4月11日 下午4:02:25
 * @Description: 获取文件类型工具类
 */
public class FileTypeUtil {
	
	/**
	 * @Title: getFileType 
	 * @Description: 获取文件类型
	 * @param @param filepath 文件路径
	 * @param @return   
	 * @return String 返回文件类型
	 * @throws
	 */
	public static String getFileType(String filepath) {
		File f = new File(filepath);
		String filetype = null;
		if (f.exists()) {
			filetype = getFileByFile(f);
		}else{
		}
		return filetype;
	}
	
	
	
	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

	private FileTypeUtil() {
	}

	static {
		getAllFileType(); // 初始化文件类型信息
	}

	/**
	 * @Title: getAllFileType 
	 * @Description: 常见文件头信息
	 * @param    
	 * @return void    
	 * @throws
	 */
	private static void getAllFileType() {
		FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
		FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
		FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)
		FILE_TYPE_MAP.put("tif", "49492A00"); // TIFF (tif)
		FILE_TYPE_MAP.put("bmp", "424D"); // Windows Bitmap (bmp)
		FILE_TYPE_MAP.put("dwg", "41433130"); // CAD (dwg)
		FILE_TYPE_MAP.put("pdf", "255044462D312E"); // Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("office07", "504B0304"); 
		FILE_TYPE_MAP.put("office03", "D0CF11E0"); 
		FILE_TYPE_MAP.put("swf", "465753");
		FILE_TYPE_MAP.put("mp3", "494433030");
		FILE_TYPE_MAP.put("flv", "464c560");
		FILE_TYPE_MAP.put("docx", "504b0304140006000800docx");//docx文件
		FILE_TYPE_MAP.put("doc", "docd0cf11e"); //doc,excel,xls，ppt相同文件头
	}
	
	/**
	 * Discription:[getImageFileType,获取图片文件实际类型,若不是图片则返回null]
	 * 
	 * @param File
	 * @return fileType
	 */
	public final static String getImageFileType(File f) {
		if (isImage(f)) {
			try {
				ImageInputStream iis = ImageIO.createImageInputStream(f);
				Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
				if (!iter.hasNext()) {
					return null;
				}
				ImageReader reader = iter.next();
				iis.close();
				return reader.getFormatName();
			} catch (IOException e) {
				return null;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Discription:[getFileByFile,获取文件类型,包括图片,若格式不是已配置的,则返回null]
	 * 
	 * @param file
	 * @return fileType
	 */
	public final static String getFileByFile(File file) {
		String filetype = null;
		byte[] b = new byte[50];
		try {
			InputStream is = new FileInputStream(file);
			is.read(b);
			filetype = getFileTypeByStream(b);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filetype;
	}

	/**
	 * Discription:[getFileTypeByStream]
	 * 
	 * @param b
	 * @return fileType
	 */
	public final static String getFileTypeByStream(byte[] b) {
		String filetypeHex = String.valueOf(getFileHexString(b));
		Iterator<Entry<String, String>> entryiterator = FILE_TYPE_MAP.entrySet().iterator();
		while (entryiterator.hasNext()) {
			Entry<String, String> entry = entryiterator.next();
			String fileTypeHexValue = entry.getValue();
			if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Discription:[isImage,判断文件是否为图片]
	 * 
	 * @param file
	 * @return true 是 | false 否
	 */
	public static final boolean isImage(File file) {
		boolean flag = false;
		try {
			BufferedImage bufreader = ImageIO.read(file);
			int width = bufreader.getWidth();
			int height = bufreader.getHeight();
			if (width == 0 || height == 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (IOException e) {
			flag = false;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Discription:[getFileHexString]
	 * 
	 * @param b
	 * @return fileTypeHex
	 */
	public final static String getFileHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
