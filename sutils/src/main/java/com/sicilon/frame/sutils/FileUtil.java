package com.sicilon.frame.sutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类中封装一些常用的文件操作。 所有方法都是静态方法，不需要生成此类的实例， 为避免生成此类的实例，构造方法被申明为private类型的。
 * 
 * @since 1.0
 */

public class FileUtil {
	/**
	 * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
	 */
	private FileUtil() {

	}

	/**
	 * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
	 * <b>目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。</b>
	 * 
	 * @param file
	 *            需要修改最后访问时间的文件。
	 * @since 1.0
	 */
	public static void touch(File file) {
		long currentTime = System.currentTimeMillis();
		if (!file.exists()) {
			System.err.println("file not found:" + file.getName());
			System.err.println("Create a new file:" + file.getName());
			try {
				if (file.createNewFile()) {
					System.out.println("Succeeded!");
				} else {
					System.err.println("Create file failed!");
				}
			} catch (IOException e) {
				System.err.println("Create file failed!");
				e.printStackTrace();
			}
		}
		boolean result = file.setLastModified(currentTime);
		if (!result) {
			System.err.println("touch failed: " + file.getName());
		}
	}

	/**
	 * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
	 * <b>目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。</b>
	 * 
	 * @param fileName
	 *            需要修改最后访问时间的文件的文件名。
	 * @since 1.0
	 */
	public static void touch(String fileName) {
		File file = new File(fileName);
		touch(file);
	}

	/**
	 * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
	 * <b>目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。</b>
	 * 
	 * @param files
	 *            需要修改最后访问时间的文件数组。
	 * @since 1.0
	 */
	public static void touch(File[] files) {
		for (int i = 0; i < files.length; i++) {
			touch(files[i]);
		}
	}

	/**
	 * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
	 * <b>目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。</b>
	 * 
	 * @param fileNames
	 *            需要修改最后访问时间的文件名数组。
	 * @since 1.0
	 */
	public static void touch(String[] fileNames) {
		File[] files = new File[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			files[i] = new File(fileNames[i]);
		}
		touch(files);
	}

	/**
	 * 判断指定的文件是否存在。
	 * 
	 * @param fileName
	 *            要判断的文件的文件名
	 * @return 存在时返回true，否则返回false。
	 * @since 1.0
	 */
	public static boolean isFileExist(String fileName) {
		return new File(fileName).isFile();
	}

	/**
	 * 创建路径
	 * @param paths
	 */
	public static void createPaths(String paths) {
		File dir = new File(paths);
		if (!dir.exists())
			dir.mkdirs();
	}

	/**
	 * 创建文件的目录结构。 <b>注意：可能会在返回false的时候创建部分父目录。</b>
	 * 
	 * @param file
	 *            要创建的目录
	 * @return 完全创建成功时返回true，否则返回false。
	 * @since 1.0
	 */
	public static boolean createFilePath(File file) {
		File parent = file.getParentFile();
		if (parent != null) {
			return parent.mkdirs();
		}
		return false;
	}


	/**
	 * 清空指定目录中的文件。 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
	 * 另外这个方法不会迭代删除，即不会删除子目录及其内容。
	 * 
	 * @param directory
	 *            要清空的目录
	 * @return 目录下的所有文件都被成功删除时返回true，否则返回false.
	 * @throws FileNotFoundException 
	 * @since 1.0
	 */
	public static boolean emptyDirectory(File directory) throws FileNotFoundException {
		boolean result = true;
		
		/*
		 * 如果文件不存在,抛出文件不存在异常
		 */
		if(!directory.exists()){
			throw new FileNotFoundException(directory.getPath()+",此路径不存在");
		}
		
		/*
		 * 如果文件目录下,
		 */
		File[] entries = directory.listFiles();
		if(entries == null ){
			return true;
		}
		
		/*
		 * 当遍历删除时,存在没有被删除的文件,将状态标识为false
		 */
		for (int i = 0; i < entries.length; i++) {
			if (!entries[i].delete()) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 清空指定目录中的文件。 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
	 * 另外这个方法不会迭代删除，即不会删除子目录及其内容。
	 * 
	 * @param directoryName
	 *            要清空的目录的目录名
	 * @return 目录下的所有文件都被成功删除时返回true，否则返回false。
	 * @throws FileNotFoundException 
	 * @since 1.0
	 */
	public static boolean emptyDirectory(String directoryName) throws FileNotFoundException {
		File dir = new File(directoryName);
		return emptyDirectory(dir);
	}

	/**
	 * 删除指定目录及其中的所有内容。
	 * 
	 * @param dirName
	 *            要删除的目录的目录名
	 * @return 删除成功时返回true，否则返回false。
	 * @since 1.0
	 */
	public static boolean deleteDirectory(String dirName) {
		return deleteDirectory(new File(dirName));
	}

	/**
	 * 删除指定目录及其中的所有内容。
	 * 
	 * @param dir
	 *            要删除的目录
	 * @return 删除成功时返回true，否则返回false。
	 * @since 1.0
	 */
	public static boolean deleteDirectory(File dir) {
		/*
		 * 文件为空或者所传file不是目录,抛出异常
		 */
		if ((dir == null) || !dir.isDirectory()) {
			throw new IllegalArgumentException("Argument " + dir + " is not a directory. ");
		}
		
		// 获取子文件列表
		File[] entries = dir.listFiles();
		int sz = entries.length;

		/*
		 * 递归删除子文件和子目录
		 */
		for (int i = 0; i < sz; i++) {
			if (entries[i].isDirectory()) {
				if (!deleteDirectory(entries[i])) {
					return false;
				}
			} else {
				if (!entries[i].delete()) {
					return false;
				}
			}
		}

		/*
		 * 删除本文件
		 */
		if (!dir.delete()) {
			return false;
		}
		return true;
	}


	/**
	 * 列出目录中的所有内容，包括其子目录中的内容。
	 * 
	 * @param file
	 *            要列出的目录
	 * @param filter
	 *            过滤器
	 * @return 目录内容的文件数组。
	 * @since 1.0
	 */
	public static File[] listAll(File file, javax.swing.filechooser.FileFilter filter) {
		List<File> list= new ArrayList<File>();
		File[] files;
		
		// 控制传入的文件必须是一个真实存在的目录
		if (!file.exists() || file.isFile()) {
			return null;
		}
		//将目录中的所有文件递归加入到文件集合中 
		list(list, file, filter);
		// 初始化数组长度
		files = new File[list.size()];
		// 将list转换为数组
		list.toArray(files);
		return files;
	}

	/**
	 * 将目录中的内容添加到列表。
	 * 
	 * @param list
	 *            文件列表
	 * @param filter
	 *            过滤器
	 * @param file
	 *            目录
	 */
	private static void list(List<File> list, File file, javax.swing.filechooser.FileFilter filter) {
		if (filter.accept(file)) {
			list.add(file);
			if (file.isFile()) {
				return;
			}
		}
		if (file.isDirectory()) {
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				list(list, files[i], filter);
			}
		}

	}

	/**
	 * 从文件路径得到文件名。
	 * 
	 * @param filePath
	 *            文件的路径，可以是相对路径也可以是绝对路径
	 * @return 对应的文件名
	 * @since 1.0
	 */
	public static String getFileNameByPath(String filePath) {
		File file = new File(filePath);
		return file.getName();
	}

	/**
	 * 从文件名得到以项目为根的绝对路径。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 对应的文件路径
	 * @since 1.0
	 */
	public static String getPathByFileName(String fileName) {
		File file = new File(fileName);
		return file.getAbsolutePath();
	}
	

	/**
	 * 获取文件的后缀名
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件名中的类型部分
	 * @since 1.0
	 */
	public static String getTypePart(String fileName) {
		int point = fileName.lastIndexOf('.');
		int length = fileName.length();
		if (point == -1 || point == length - 1) {
			return "";
		} else {
			return fileName.substring(point + 1, length);
		}
	}

	/**
	 * 获取文件的后缀名。
	 * 
	 * @param file
	 *            文件
	 * @return 文件名中的类型部分
	 * @since 1.0
	 */
	public static String getFileType(File file) {
		return getTypePart(file.getName());
	}

	/**
	 * 获取文件简易名称,不携带后缀名
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件名中的名字部分
	 * @since 1.0
	 */
	public static String getFileSimpleName(String fileName) {
		int point = getPathLsatIndex(fileName);
		int length = fileName.length();
		if (point == -1) {
			return fileName;
		} else if (point == length - 1) {
			int secondPoint = getPathLsatIndex(fileName, point - 1);
			if (secondPoint == -1) {
				if (length == 1) {
					return fileName;
				} else {
					return fileName.substring(0, point);
				}
			} else {
				return fileName.substring(secondPoint + 1, point);
			}
		} else {
			return fileName.substring(point + 1);
		}
	}

	/**
	 * 得到文件名中的父路径部分。 
	 * @param fileName
	 *            文件名
	 * @return 父路径，不存在或者已经是父目录时返回""
	 * @since 1.0
	 */
	public static String getParentPath(String fileName) {
		int point = getPathLsatIndex(fileName);
		int length = fileName.length();
		if (point == -1) {
			return "";
		} else if (point == length - 1) {
			int secondPoint = getPathLsatIndex(fileName, point - 1);
			if (secondPoint == -1) {
				return "";
			} else {
				return fileName.substring(0, secondPoint);
			}
		} else {
			return fileName.substring(0, point);
		}
	}

	/**
	 * 得到路径分隔符在文件路径中最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 路径分隔符在路径中最后出现的位置，没有出现时返回-1。
	 * @since 1.0
	 */
	private static int getPathLsatIndex(String fileName) {
		int point = fileName.lastIndexOf('/');
		if (point == -1) {
			point = fileName.lastIndexOf('\\');
		}
		return point;
	}

	/**
	 * 得到路径分隔符在文件路径中指定位置前最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @param fromIndex
	 *            开始查找的位置
	 * @return 路径分隔符在路径中指定位置前最后出现的位置，没有出现时返回-1。
	 * @since 1.0
	 */
	private static int getPathLsatIndex(String fileName, int fromIndex) {
		int point = fileName.lastIndexOf('/', fromIndex);
		if (point == -1) {
			point = fileName.lastIndexOf('\\', fromIndex);
		}
		return point;
	}

	/**
	 * 将文件名中的类型部分去掉。
	 * 
	 * @param filename
	 *            文件名
	 * @return 去掉类型部分的结果
	 * @since 1.0
	 */
	public static String trimType(String filename) {
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			return filename.substring(0, index);
		} else { 
			return filename;
		}
	}

	/**
	 * 得到相对路径。 文件名不是目录名的子节点时返回文件名。
	 * 
	 * @param pathName
	 *            目录名
	 * @param fileName
	 *            文件名
	 * @return 得到文件名相对于目录名的相对路径，目录下不存在该文件时返回文件名
	 * @since 1.0
	 */
	public static String getSubpath(String pathName, String fileName) {
		int index = fileName.indexOf(pathName);
		if (index != -1) {
			return fileName.substring(index + pathName.length() + 1);
		} else {
			return fileName;
		}
	}

	
	/**
	 * 读取文件的内容 读取指定文件的内容
	 * 
	 * @param path
	 *            为要读取文件的绝对路径
	 * @return 以行读取文件后的内容。
	 * @since 1.0
	 */
	public static final String getFileContent(String path,String charset) throws IOException {
		StringBuffer filecontent = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(new FileInputStream(path), charset);
			br = new BufferedReader(isr); // 建立BufferedReader对象，并实例化为br
			String line; 
			while ((line = br.readLine())!= null) {
				filecontent.append(line).append("\n") ;
			}

		} catch (IOException e) {
			throw e;
		}finally {
			br.close(); // 关闭BufferedReader对象
			isr.close();
		}
		return filecontent.toString();
	}


	

	/**
	 *  拷贝文件
	 * @param in 输入文件
	 * @param out 作为输出的文件
	 * @return
	 * @throws Exception
	 */
	public static final boolean CopyFile(File in, File out) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(in);
			FileOutputStream fos = new FileOutputStream(out);
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
			return true;
		} catch (IOException ie) {
			ie.printStackTrace();
			return false;
		}
	}

	/**
	 * 拷贝文件
	 * @param infile 输入文件名称
	 * @param outfile 输出文件的名称
	 * @return
	 * @throws Exception
	 */
	public static final boolean CopyFile(String infile, String outfile) throws Exception {
		try {
			File in = new File(infile);
			File out = new File(outfile);
			return CopyFile(in, out);
		} catch (IOException ie) {
			ie.printStackTrace();
			return false;
		}

	}

}
