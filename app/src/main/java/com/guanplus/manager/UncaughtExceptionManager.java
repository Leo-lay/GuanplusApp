package com.guanplus.manager;

import android.os.Process;

import com.guanplus.utils.LogUtils;
import com.guanplus.utils.StringUtils;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 全局异常捕获对象
 * 
 * @author scleo@concordya.com
 */
public class UncaughtExceptionManager implements UncaughtExceptionHandler
{
	private static UncaughtExceptionManager	mUCEManager;

	/***
	 * 私有化构造函数，创建单例
	 */
	private UncaughtExceptionManager()
	{
	}

	/**
	 * 获取实体对象
	 * 
	 * @return
	 */
	public static UncaughtExceptionManager getInstanceHandler()
	{
		if (mUCEManager == null)
		{
			return new UncaughtExceptionManager();
		}
		return mUCEManager;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex)
	{
		StringBuffer sb = new StringBuffer();
		if (ex != null)
		{
			String msg = ex.getMessage();
			if (!StringUtils.isEmpty(msg))
			{
				sb.append(msg);
			}
			sb.append("\r\n");
			sb.append(thread.getName());
			sb.append("\r\n");
			StackTraceElement[] elements = ex.getStackTrace();
			for (StackTraceElement element : elements)
			{
				sb.append(element.toString());
				sb.append("\r\n");
			}
			sb.append("Cause:");
			Throwable cause = ex.getCause();
			if (cause != null)
			{
				sb.append(cause.toString());
				sb.append("\r\n");
				elements = cause.getStackTrace();
				for (StackTraceElement stack : elements)
				{
					sb.append(stack.toString());
					sb.append("\r\n");
				}
			}
		}
		LogUtils.e("carsh�?" + sb.toString());
		SaveLog(sb.toString());
		// CommonUtil.
		// 隐藏软键�?
	/*	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);*/
		Process.killProcess(Process.myPid());
	}

	/***
	 * 保存日志发�?�至服务�?
	 */
	private void SaveLog(String log)
	{

	}
}
