package com.guanplus.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.guanplus.manager.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @author Scleo
 * 
 */
public class CommonUtil
{
	/**
	 * Dialog显示�?
	 * 
	 * @param context
	 * @param message
	 */
	public static void showInfoDialog(Context context, String message)
	{
		showInfoDialog(context, message, "提示", "确定", null);
	}

	public static void showInfoDialog(Context context, String message, String titleStr, String positiveStr,
			DialogInterface.OnClickListener onClickListener)
	{
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
		localBuilder.setTitle(titleStr);
		localBuilder.setMessage(message);
		if (onClickListener == null)
			onClickListener = new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{

				}
			};
		localBuilder.setPositiveButton(positiveStr, onClickListener);
		localBuilder.show();
	}

	/**
	 * MD5加密处理
	 * 
	 * @param paramString
	 * @return
	 */
	public static String md5(String paramString)
	{
		String returnStr;
		try
		{
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramString.getBytes());
			returnStr = byteToHexString(localMessageDigest.digest());
			return returnStr;
		}
		catch (Exception e)
		{
			return paramString;
		}
	}

	/**
	 * 将指定byte数组转换�?16进制字符�?
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte[] b)
	{
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1)
			{
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**
	 * 判断当前是否有可用的网络以及网络类型
	 * 
	 * @param context
	 * @return 0：无网络 1：WIFI 2：CMWAP 3：CMNET
	 */
	public static int isNetworkAvailable(Context context)
	{
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null)
		{
			return 0;
		}
		else
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
			{
				for (int i = 0; i < info.length; i++)
				{
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI)
						{
							return 1;
						}
						else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
						{
							String extraInfo = netWorkInfo.getExtraInfo();
							if ("cmwap".equalsIgnoreCase(extraInfo) || "cmwap:gsm".equalsIgnoreCase(extraInfo))
							{
								return 2;
							}
							return 3;
						}
					}
				}
			}
		}
		return 0;
	}

	/** 当前网络是否可用 */
	public static boolean isNetWorkNormal(Context context)
	{
		if (isNetworkAvailable(context) == 0)
			return false;
		return true;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate(String format)
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 * **/
	public static String getStringDateYDM()
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return 返回毫秒�?
	 */
	public static long getCurrentTime()
	{
		return System.currentTimeMillis();
	}


	/**
	 * 根据手机的分辨率�? dp 的单�? 转成�? px(像素)
	 */
	public static int dip2px(Context context, float dpValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率�? px(像素) 的单�? 转成�? dp
	 */
	public static int px2dip(Context context, float pxValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率�? sp(像素) 的单�? 转成�? px
	 */
	public static int sp2px(Context context, float spValue)
	{
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	
	/**
	 * 根据手机的分辨率�? sp(像素) 的单�? 转成�? px
	 */
	public static int px2sp(Context context, float spValue)
	{
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue / fontScale + 0.5f);
	}
	/**
	 * 创建图片
	 * 
	 * @param d
	 * @param p
	 * @return
	 */
	private static Drawable createDrawable(Drawable d, Paint p)
	{
		BitmapDrawable bd = (BitmapDrawable) d;
		Bitmap b = bd.getBitmap();
		Bitmap bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(), bd.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(b, 0, 0, p); // 关键代码，使用新的Paint画原图，
		return new BitmapDrawable(bitmap);
	}

	/** 设置Selector�? */
	public static StateListDrawable createSLD(Context context, Drawable drawable)
	{
		StateListDrawable bg = new StateListDrawable();
		int brightness = 50 - 127;
		ColorMatrix cMatrix = new ColorMatrix();
		cMatrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0, brightness,// 改变亮度
				0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });

		Paint paint = new Paint();
		paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

		Drawable normal = drawable;
		Drawable pressed = createDrawable(drawable, paint);
		bg.addState(new int[] { android.R.attr.state_pressed, }, pressed);
		bg.addState(new int[] { android.R.attr.state_focused, }, pressed);
		bg.addState(new int[] { android.R.attr.state_selected }, pressed);
		bg.addState(new int[] {}, normal);
		return bg;
	}

	/**
	 * 将文件转换成Bitmap
	 * 
	 * @param ct
	 * @param fileName
	 * @return
	 */
	public static Bitmap getImageFromAssetsFile(Context ct, String fileName)
	{
		Bitmap image = null;
		AssetManager am = ct.getAssets();
		try
		{
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * 获取SharedPreferences文件中的token
	 * 
	 * @param
	 */
	public static boolean hasToken(Context ct)
	{
		String token = SharedPreferencesUtils.getString(ct, "token", "");
		if (TextUtils.isEmpty(token))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * 设置Listview中子项的高度
	 * 
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView)
	{
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
		{
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++)
		{ // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽�?
			totalHeight += listItem.getMeasuredHeight(); // 统计�?有子项的总高�?
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 通知服务器，发�?�短信验证码到指定号�?
	 */
	/*
	 * public static String noticeSMSCode(String number) {
	 * String url = ContentValue.SERVER_URI+"/"+ContentValue.VERICAL_REQUEST;
	 * NetUtils net = new NetUtils();
	 * return net.doPostOfHttpClientFor(url, "="+number);
	 * }
	 */
	/**
	 * 将对象转换成json字符�?
	 * 
	 * @param t bean对象
	 * @return
	 */
	public static <T> String bean2Json(T t)
	{
		Gson gs = new Gson();
		String json = gs.toJson(t);
		return json;
	}

	/**
	 * 将json映射成bean对象
	 * 
	 * @param result json字符�?
	 * @param clazz bean对象字节�?
	 * @return
	 */
	public static <T> T json2Bean(String result, Class<T> clazz)
	{
		if (StringUtils.isEmpty(result))
			return null;
		Gson gs = new Gson();
		return gs.fromJson(result, clazz);
	}

	/**
	 * 将json映射成list集合
	 * 
	 * @param result json字符�?
	 * @param clazz bean对象字节�?
	 * @return
	 */
	public static <T> List<T> json2List(String result, Type type)
	{
		Gson gson = new Gson();
		// gson.f
		// gson.
		List<T> list = gson.fromJson(result, type);
		return list;
	}

	/**
	 * 将字符型的毫米�?�转换成Date
	 * 
	 * @param mills
	 * @return
	 */
	public static String formate2String(String mills)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long l = Long.parseLong(mills);
		Date date;
		date = new Date(l);
		String format = sdf.format(date);
		return format;
	}


	/****
	 * 获取图片
	 * 
	 * @param txt
	 * @param key
	 * @param data
	 * @return
	 */
	public static Bitmap getBitmap(Context txt, String key, Intent data)
	{
		if (!FileUtils.isSDCardAvailable())
		{ // �?测sd是否可用
			Log.i("TestFile", "SD card is not avaiable/writeable right now.");
			PromptManager.showToast(txt, "SdCard 不可�?");
			return null;
		}
		String fileName = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
		Bundle bundle = data.getExtras();
		Bitmap bitmap = (Bitmap) bundle.get(key);// 获取相机返回的数据，并转换为Bitmap图片格式
		FileOutputStream b = null;
		File file = new File(FileUtils.getExternalStoragePath(), fileName);
		file.mkdirs();// 创建文件�?
		try
		{
			b = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文�?
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (b != null)
				{
					b.flush();
					b.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return bitmap;
	}


	/***
	 * 通过Uri获取图片路劲
	 * 
	 * @param context
	 * @param uri
	 * @return
	 */
	public static String getImgPathByUri(Context context, Uri uri)
	{
		String path = "";
		String[] filePathColumns = new String[] { MediaStore.Images.Media.DATA };
		Cursor cursor = context.getContentResolver().query(uri, filePathColumns, null, null, null);
		if (cursor != null)
		{
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumns[0]);
			path = cursor.getString(columnIndex);
		}
		return path;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context)
	{
		if (context == null)
			return 0;
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenHeight = dm.heightPixels;
		return screenHeight;
	}

	/**
	 * 判断是否具备创建单子权限
	 * 
	 * @param context
	 */
	public static boolean checkCreateRole(Context ct)
	{
		if (!BaseApplication.getApplication().getConfigInfo(ContentValue.USER_CONFIG_ROLES).contains("0"))
		{
			PromptManager.showToast(ct, "没有权限，无法创建单�?!!!");
			return false;
		}
		return true;
	}

	public static boolean checkApprover(String approverId)
	{
		if (BaseApplication.getApplication().getConfigInfo(ContentValue.USER_CONFIG_ID).equals(approverId))
		{
			return true;
		}
		return false;
	}
}
