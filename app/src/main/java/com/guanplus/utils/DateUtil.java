package com.guanplus.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;


/**
 * 日期工具�?
 * 
 * @author Toby
 * 
 */
public class DateUtil
{
	/**
	 * 格式化时间戳
	 * 
	 * @return 2013-11-12 11:13
	 */
	public static String formatTime(long time)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
		Date date = new Date();
		date.setTime(time);
		return format.format(date);
	}


	/**
	 * 格式化时间戳
	 * 
	 * @return 2013-11-12
	 */
	public static String formatTime1(long time)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date date = new Date();
		// if(time==null||time==0)
		date.setTime(time == 0 ? System.currentTimeMillis() : time);
		return format.format(date);
	}

	/**
	 * 格式化时间戳
	 * 
	 * @return 03-17
	 */
	public static String formatTime2(long time)
	{
		SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.getDefault());
		Date date = new Date();
		date.setTime(time);
		return format.format(date);
	}

	/**
	 * 格式化时间戳
	 * 
	 * @return 2015�?2�?
	 */
	public static String formatTime3(Context ct, long time)
	{
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy" + ct.getString(R.string.year_hint) + "MM" + ct.getString(R.string.month_hint),
				Locale.getDefault());
		Date date = new Date();
		date.setTime(time);
		return format.format(date);*/
		return null;
	}

	/**
	 * 格式化时间戳
	 * 
	 * @return 20 (天，数字)
	 */
	public static String formatTime4(long time)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd", Locale.getDefault());
		Date date = new Date();
		date.setTime(time);
		return format.format(date);
	}

	/**
	 * 格式化时间戳
	 * 
	 * @return 2（月份）
	 */
	public static String formatTime5(long time)
	{
		SimpleDateFormat format = new SimpleDateFormat("MM", Locale.getDefault());
		Date date = new Date();
		date.setTime(time);
		return format.format(date);
	}

	/**
	 * 格式化日�? 2015-4-13
	 * 
	 * @return 时间�?
	 */
	public static long date2TimeStamp(String dateStr)
	{
		if (StringUtils.isEmpty(dateStr))
		{
			return 0;
		}

		long re_time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date d;
		try
		{
			d = sdf.parse(dateStr);
			re_time = d.getTime();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return re_time;
	}

}
