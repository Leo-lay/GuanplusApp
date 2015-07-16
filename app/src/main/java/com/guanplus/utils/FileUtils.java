package com.guanplus.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;

/**
 * @author Scleo
 */
public class FileUtils
{

	public static final String		ROOT_DIR		= "Concordya";
	public static final String		IMAGES_DIR		= "Images";
	public static final String		DOWNLOAD_DIR	= "Download";
	public static final String		CACHE_DIR		= "Cache";
	public static final String		DOCUMENTS_DIR	= "Files";
	public static final String		ICON_DIR		= "Icon";
	public static final String		RECORDER_DIR	= "Recorder";
	public static final String		IMAGE_PNG		= ".png";
	public static final String		IMAGE_JPG		= ".jpg";

	/** 判断SD卡是否挂�? */
	public static boolean isSDCardAvailable()
	{
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	/** 获取下载目录 */
	public static String getDownloadDir()
	{
		return getDir(DOWNLOAD_DIR);
	}

	/** 获取icon目录 */
	public static String getIconDir()
	{
		return getDir(ICON_DIR);
	}

	/** 创建文件�? */
	public static boolean createDirs(String dirPath)
	{
		File file = null;
		try
		{
			file = new File(dirPath);
			if (!file.exists())
			{
				return file.mkdirs();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	/** 创建文件 */
	public static boolean createFile(String dirPath, String fileName)
	{
		File file = null;
		try
		{
			file = new File(dirPath);
			if (!file.exists() || !file.isDirectory())
			{
				createDirs(dirPath);
				return file.createNewFile();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return true;
	}

	/** 判断文件是否可写 */
	public static boolean isWriteable(String path)
	{
		try
		{
			if (StringUtils.isEmpty(path))
			{
				return false;
			}
			File f = new File(path);
			return f.exists() && f.canWrite();
		}
		catch (Exception e)
		{
			LogUtils.e(e);
			return false;
		}
	}

	/**
	 * 把数据写入文�?
	 * 
	 * @param is 数据�?
	 * @param path 文件路径
	 * @param recreate 如果文件存在，是否需要删除重�?
	 * @return 是否写入成功
	 */
	public static boolean writeFile(InputStream is, String path, boolean recreate)
	{
		boolean res = false;
		File f = new File(path);
		FileOutputStream fos = null;
		try
		{
			if (recreate && f.exists())
			{
				f.delete();
			}
			if (!f.exists() && null != is)
			{
				File parentFile = new File(f.getParent());
				parentFile.mkdirs();
				int count = -1;
				byte[] buffer = new byte[4*1024];
				fos = new FileOutputStream(f);
				while ((count = is.read(buffer)) != -1)
				{
					fos.write(buffer, 0, count);
				}
				res = true;
			}
		}
		catch (Exception e)
		{
			LogUtils.e(e);
		}
		finally
		{
			IOUtils.close(fos);
			IOUtils.close(is);
		}
		return res;
	}

	/**
	 * 把字节数组写入文�?
	 * 
	 * @param content �?要写入的字符�?
	 * @param path 文件路径名称
	 * @param append 是否以添加的模式写入
	 * @return 是否写入成功
	 */
	public static boolean writeFile(byte[] content, String path, boolean append)
	{
		boolean res = false;
		File f = new File(path);
		RandomAccessFile raf = null;
		try
		{
			if (f.exists())
			{
				if (!append)
				{
					f.delete();
					f.createNewFile();
				}
			}
			else
			{
				f.createNewFile();
			}
			if (f.canWrite())
			{
				raf = new RandomAccessFile(f, "rw");
				raf.seek(raf.length());
				raf.write(content);
				res = true;
			}
		}
		catch (Exception e)
		{
			LogUtils.e(e);
		}
		finally
		{
			IOUtils.close(raf);
		}
		return res;
	}

	/**
	 * 把字符串数据写入文件
	 * 
	 * @param content �?要写入的字符�?
	 * @param path 文件路径名称
	 * @param append 是否以添加的模式写入
	 * @return 是否写入成功
	 */
	public static boolean writeFile(String content, String path, boolean append)
	{
		return writeFile(content.getBytes(), path, append);
	}

	/***
	 * 得到读取文件流数�?
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static InputStream getStreamFromFile(String path) throws Exception
	{

		FileInputStream fis = null;
		File f = new File(path);
		if (!f.exists() || !f.isFile())
		{
			f.createNewFile();
		}
		fis = new FileInputStream(f);
		return fis;
	}

	/***
	 * 读取文件
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String filePath)
	{
		// 得到文件读取流对�?
		InputStreamReader isr = null;
		String result = "";
		BufferedReader br = null;
		StringWriter sw = null;
		try
		{
			isr = new InputStreamReader(getStreamFromFile(filePath));
			br = new BufferedReader(isr);
			// 内存的输出流
			sw = new StringWriter();
			String str;
			while ((str = br.readLine()) != null)
			{
				sw.write(str);
			}
			// 把内存流的对�? 转换成字符串
			result = sw.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{// 关闭流资�?
			if (isr != null)
				IOUtils.close(isr);
			if (br != null)
				IOUtils.close(br);
			if (sw != null)
				IOUtils.close(sw);
		}
		return result;
	}

	/** 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录 */
	public static String getDir(String name)
	{
		StringBuilder sb = new StringBuilder();
		if (isSDCardAvailable())
		{
			sb.append(getExternalStoragePath());
		}
		else
		{
			sb.append(getCachePath());
		}
		sb.append(name);
		sb.append(File.separator);
		String path = sb.toString();
		if (createDirs(path))
		{
			return path;
		}
		else
		{
			return null;
		}
	}

	/***
	 * 获取应用手机内存目录
	 * 
	 * @return
	 */
	public static String getCachePath()
	{
		File f = UIUtils.getContext().getCacheDir();
		if (null == f)
		{
			return null;
		}
		else
		{
			return f.getAbsolutePath() + "/";
		}
	}

	public static String getUriPath(Context ct, Uri uri)
	{
		String path = "";
		// String[] pro ={MediaStore.Images.Media.DATA};
		// act
		// Cursor cursor = act.managedQuery(uri, pro, null, null, null);
		Cursor cursor = ct.getContentResolver().query(uri, null, null, null, null);
		if (cursor != null && cursor.moveToFirst())
		{
			for (int i = 0; i < cursor.getColumnCount(); i++)
			{
			}
			// int index = cursor.getColumnIndexOrThrow(pro[0]);
			return path = cursor.getString(0);
		}
		return path;
	}

	/** 获取SD下的应用目录 */
	public static String getExternalStoragePath()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
		sb.append(File.separator);
		sb.append(ROOT_DIR);
		sb.append(File.separator);
		return sb.toString();
	}

	/** 获取SD下的图片目录 */
	public static String getImagesPath()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getExternalStoragePath());
		sb.append(IMAGES_DIR);
		sb.append(File.separator);
		return sb.toString();
	}

	/** 获取SD下的录音目录 */
	public static String getRecorderPath()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getExternalStoragePath());
		sb.append(RECORDER_DIR);
		sb.append(File.separator);
		return sb.toString();
	}

	/** 获取SD下的文件目录 */
	public static String getDocumentPath()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getExternalStoragePath());
		sb.append(DOCUMENTS_DIR);
		sb.append(File.separator);
		return sb.toString();
	}

	/** 复制文件，可以�?�择是否删除源文�? */
	public static boolean copyFile(String srcPath, String destPath, boolean deleteSrc)
	{
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		return copyFile(srcFile, destFile, deleteSrc);
	}

	/** 复制文件，可以�?�择是否删除源文�? */
	public static boolean copyFile(File srcFile, File destFile, boolean deleteSrc)
	{
		if (!srcFile.exists() || !srcFile.isFile())
		{
			return false;
		}
		InputStream in = null;
		OutputStream out = null;
		try
		{
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = in.read(buffer)) > 0)
			{
				out.write(buffer, 0, i);
				out.flush();
			}
			if (deleteSrc)
			{
				srcFile.delete();
			}
		}
		catch (Exception e)
		{
			LogUtils.e(e);
			return false;
		}
		finally
		{
			IOUtils.close(out);
			IOUtils.close(in);
		}
		return true;
	}

	/** 改名 */
	public static boolean copy(String src, String des, boolean delete)
	{
		File file = new File(src);
		if (!file.exists())
		{
			return false;
		}
		File desFile = new File(des);
		FileInputStream in = null;
		FileOutputStream out = null;
		try
		{
			in = new FileInputStream(file);
			out = new FileOutputStream(desFile);
			byte[] buffer = new byte[1024];
			int count = -1;
			while ((count = in.read(buffer)) != -1)
			{
				out.write(buffer, 0, count);
				out.flush();
			}
		}
		catch (Exception e)
		{
			LogUtils.e(e);
			return false;
		}
		finally
		{
			IOUtils.close(in);
			IOUtils.close(out);
		}
		if (delete)
		{
			file.delete();
		}
		return true;
	}

	/***
	 * 获取文件�?
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath)
	{
		if (StringUtils.isEmpty(filePath))
			return "";
		return filePath.substring(filePath.lastIndexOf(File.separator) + 1).trim();
	}

	/**
	 * 保存图片的方�?
	 * 保存到sdcard
	 * 
	 * @throws IOException
	 */
	public static void savePic2SDCard(Bitmap bitmap, String path, String name)
	{
		if (isSDCardAvailable())
		{
			createDirs(path);
			File file = null;
			FileOutputStream fos = null;
			try
			{
				file = new File(path, name + IMAGE_JPG);
				fos = new FileOutputStream(file);
				if (bitmap != null)
				{
					if (bitmap.compress(CompressFormat.JPEG, 100, fos))
					{
						fos.flush();
					}
				}
			}
			catch (FileNotFoundException e)
			{
				file.delete();
				e.printStackTrace();
			}
			catch (IOException e)
			{
				file.delete();
				e.printStackTrace();
			}
			finally
			{
				IOUtils.close(fos);
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param filePathName
	 * @return
	 */
	public static boolean downloadFile(String url, String filePathName)
	{
		if (StringUtils.isEmpty(url))
		{
			LogUtils.e("downloadFile--参数url错误:" + url);
			return false;
		}
		if (StringUtils.isEmpty(filePathName))
		{
			LogUtils.e("downloadFile--参数filePathName错误:" + filePathName);
			return false;
		}
		LogUtils.d("downloadFile--参数正确");
		OutputStream output = null;
		File file = new File(filePathName);
		try
		{
			createFile(file);
			URL urlURL = new URL(url);
			output = new FileOutputStream(file);
			HttpURLConnection conn = (HttpURLConnection) urlURL.openConnection();
			InputStream input = conn.getInputStream();
			// 读取大文�?
			byte[] buffer = new byte[4 * 1024];
			while (input.read(buffer) != -1)
			{
				output.write(buffer);
			}
			LogUtils.d("返回文件大小" + file.length());
			output.flush();
			if (output != null)
				output.close();
			input.close();
		}
		catch (Exception e)
		{
			System.out.println("返回异常�?" + e);
			e.printStackTrace();
		}
		LogUtils.d("返回文件大小" + file.length());
		return true;
	}

	/** Corp image 图片 ***/
	public static File getCropImgFile(String fileName)
	{
		File file = null;
		try
		{
			file = new File(getImagesPath());
			if (!file.exists())
			{
				if (!file.mkdirs())
				{
					return null;
				}
			}
			return new File(getImagesPath(), fileName);
		}
		catch (Exception e)
		{
		}
		return file;
	}


	/**
	 * Enhancement of java.io.File#createNewFile()
	 * Create the given file. If the parent directory don't exists, we will
	 * create them all.
	 * 
	 * @param file the file to be created
	 * @return true if the named file does not exist and was successfully
	 *         created; false if the named file already exists
	 * @see File#createNewFile
	 * @throws IOException
	 */
	public static boolean createFile(File file) throws IOException
	{
		if (!file.exists())
		{
			makeDir(file.getParentFile());
		}
		return file.createNewFile();
	}

	/**
	 * Enhancement of java.io.File#mkdir()
	 * Create the given directory . If the parent folders don't exists, we will
	 * create them all.
	 * 
	 * @see File#mkdir()
	 * @param dir the directory to be created
	 */
	public static void makeDir(File dir)
	{
		if (!dir.getParentFile().exists())
		{
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

	/** 识别 图片结果存放xml文件 ***/
	public static File getOCRXmlFile(String fileName)
	{
		File file = null;
		try
		{
			String path = getDocumentPath();
			file = new File(path);
			if (!file.exists())
			{
				if (!file.mkdirs())
					return null;
			}
			File xmlFile = new File(path, "abbyy_output.xml");
			return xmlFile;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return file;
	}


	/*** 通过Uri获取SDCard路径 **/
	@SuppressLint("NewApi")
	public static String getSDPathByUri(final Context context, final Uri uri)
	{
		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri))
		{
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri))
			{
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type))
				{
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri))
			{

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri))
			{
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type))
				{
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				}
				else if ("video".equals(type))
				{
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				}
				else if ("audio".equals(type))
				{
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme()))
		{
			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme()))
		{
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 * 
	 * @param context The context.
	 * @param uri The Uri to query.
	 * @param selection (Optional) Filter used in the query.
	 * @param selectionArgs (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs)
	{

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try
		{
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst())
			{
				final int column_index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(column_index);
			}
		}
		finally
		{
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri)
	{
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri)
	{
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri)
	{
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * 使用文件通道的方式复制文�?
	 * 
	 * @param s 源文�?
	 * @param t 目标文件
	 */
	public static void copyFile(File s, File t)
	{

		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try
		{
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件�?�道
			out = fo.getChannel();// 得到对应的文件�?�道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fi.close();
				in.close();
				fo.close();
				out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 缩放图片
	 * 
	 * @param imagePath 图片全路�?
	 * @param maxWidth �?大宽
	 * @param maxHeight �?大高
	 * @return bitmap
	 */
	public static boolean zoomImage(String imagePath, double maxWidth, double maxHeight)
	{

		if (TextUtils.isEmpty(imagePath) || maxWidth <= 0 || maxHeight <= 0)
		{
			return false;
		}

		File file = new File(imagePath);

		if (!file.exists())
		{
			return false;
		}

		// 得到原始图片宽高
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imagePath, opts);
		double srcWidth = opts.outWidth;
		double srcHeight = opts.outHeight;


		// 排列宽高，大的数值在前面，小的在后面
		double tempMaxWidth = 0;
		double tempMaxHeight = 0;
		if (maxWidth > maxHeight)
		{
			tempMaxWidth = maxWidth;
			tempMaxHeight = maxHeight;
		}
		else
		{
			tempMaxWidth = maxHeight;
			tempMaxHeight = maxWidth;
		}
		// 排列宽高，大的数值在前面，小的在后面
		double tempSrcWidth = 0;
		double tempSrcHeight = 0;
		if (srcWidth > srcHeight)
		{
			tempSrcWidth = srcWidth;
			tempSrcHeight = srcHeight;
		}
		else
		{
			tempSrcWidth = srcHeight;
			tempSrcHeight = srcWidth;
		}

		// 缩放比例
		double des = -1;

		// 宽和高，均小于给定�?�，那么宽高不变
		if (tempSrcWidth <= tempMaxWidth && tempSrcHeight <= tempMaxHeight)
		{
		}
		else
		{
			// 计算比率，看哪个差距更大
			double w = tempSrcWidth / tempMaxWidth;
			double h = tempSrcHeight / tempMaxHeight;

			if (w > h)
			{
				des = w;
			}
			else
			{
				des = h;
			}
		}

		// 设置输出宽度、高�?
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = false;
		if (des != -1)
		{
			newOpts.inSampleSize = (int) des;
		}
		// 按照特定宽高，从文件读取出bitmap
		Bitmap bitmap = BitmapFactory.decodeFile(imagePath, newOpts);
		if(bitmap==null){
			return false;
		}
		
		// 二次缩放比例，大边除以大�?
		double desSecond;
		
		if (bitmap.getWidth() > bitmap.getHeight())
		{
			desSecond = tempMaxWidth / bitmap.getWidth();
		}
		else
		{
			desSecond = tempMaxWidth / bitmap.getHeight();
		}

		Matrix matrix = new Matrix();
		matrix.postScale((float) desSecond, (float) desSecond); // 长和宽放大缩小的比例

		Bitmap bitmapResult = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);


		OutputStream stream = null;
		CompressFormat type = null;
		try
		{
			stream = new FileOutputStream(imagePath);

			if (imagePath.endsWith("jpeg") || imagePath.endsWith("JPEG"))
			{
				type = CompressFormat.JPEG;
			}
			else if (imagePath.endsWith("jpg") || imagePath.endsWith("JPG"))
			{
				type = CompressFormat.JPEG;
			}
			else if (imagePath.endsWith("bmp") || imagePath.endsWith("BMP"))
			{
				type = CompressFormat.JPEG;
			}
			else if (imagePath.endsWith("png") || imagePath.endsWith("PNG"))
			{
				type = CompressFormat.PNG;
			}

			bitmapResult.compress(type, 100, stream);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/** 删除文件*/
	public static boolean delete(String localPath)
	{
		File file = new File(localPath);
		if(file.exists()){
			return file.delete();
		}
		return false;
	}
}
