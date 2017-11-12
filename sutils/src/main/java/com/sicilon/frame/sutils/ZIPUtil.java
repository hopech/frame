
package com.sicilon.frame.sutils;

import java.io.*;
import java.util.zip.*;


/**
 * @author zhangbo
 * 压缩/解压缩
 */
public class ZIPUtil
{

	public ZIPUtil()
	{
	}

	/**
	 * 压缩文件
	 * @param file 待压缩文件目录
	 * @param dest 存放路径
	 * @throws Exception
	 */
	public static void deCompress(File file, String dest)
		throws Exception
	{
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));
		zipFile(file, zos, "");
		zos.close();
	}

	public static void zipFile(File inFile, ZipOutputStream zos, String dir)
		throws IOException
	{
		if (inFile.isDirectory())
		{
			File files[] = inFile.listFiles();
			File arr$[] = files;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++)
			{
				File file = arr$[i$];
				String name = inFile.getName();
				if (!"".equals(dir))
					name = (new StringBuilder()).append(dir).append("/").append(name).toString();
				zipFile(file, zos, name);
			}

		} else
		{
			String entryName = null;
			if (!"".equals(dir))
				entryName = (new StringBuilder()).append(dir).append("/").append(inFile.getName()).toString();
			else
				entryName = inFile.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			InputStream is = new FileInputStream(inFile);
			for (int len = 0; (len = is.read()) != -1;)
				zos.write(len);

			is.close();
		}
	}

	/**
	 * 解压缩
	 * @param source 待解压缩的文件路径
	 * @param path  存放路径
	 * @throws IOException
	 */
	public static void unCompress(File source, String path)
		throws IOException
	{
		ZipFile zipFile = new ZipFile(source);
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(source));
		ZipEntry zipEntry = null;
		FileUtil.createPaths(path);
		while ((zipEntry = zipInputStream.getNextEntry()) != null) 
		{
			String fileName = zipEntry.getName();
			File temp = new File((new StringBuilder()).append(path).append("/").append(fileName).toString());
			if (!temp.getParentFile().exists())
				temp.getParentFile().mkdirs();
			OutputStream os = new FileOutputStream(temp);
			InputStream is = zipFile.getInputStream(zipEntry);
			for (int len = 0; (len = is.read()) != -1;)
				os.write(len);

			os.close();
			is.close();
		}
		zipInputStream.close();
	}
}
