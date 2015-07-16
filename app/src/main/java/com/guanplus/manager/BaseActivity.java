package com.guanplus.manager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.guanplus.utils.ContentValue;
import com.guanplus.utils.PromptManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseActivity extends FragmentActivity
{
	private static BaseActivity					mForegroundActivity	= null;
	protected static final List<BaseActivity>	mActivities			= new LinkedList<BaseActivity>();
	public Context								ct;
	protected int								layout;
	protected String							roles;
	protected EditText				etFlag;
	// 改代码
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置全屏

		ct = this;
		etFlag = new EditText(this);
		roles = BaseApplication.getApplication().getConfigInfo(ContentValue.USER_CONFIG_ROLES);
	}

	@Override
	protected void onResume()
	{
		mForegroundActivity = this;
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		mForegroundActivity = null;
		super.onPause();
	}

	protected void initData()
	{
	}

	protected abstract void initView();

	protected void initActionBar()
	{
	}

	public static void finishAll()
	{
		List<BaseActivity> copy;
		synchronized (mActivities)
		{
			copy = new ArrayList<BaseActivity>(mActivities);
		}
		for (BaseActivity activity : copy)
		{
			activity.finish();
		}
	}

	public static void finishAll(BaseActivity except)
	{
		List<BaseActivity> copy;
		synchronized (mActivities)
		{
			copy = new ArrayList<BaseActivity>(mActivities);
		}
		for (BaseActivity activity : copy)
		{
			if (activity != except)
				activity.finish();
		}
	}

	public static boolean hasActivity()
	{
		return mActivities.size() > 0;
	}

	public static BaseActivity getForegroundActivity()
	{
		return mForegroundActivity;
	}

	public static BaseActivity getCurrentActivity()
	{
		List<BaseActivity> copy;
		synchronized (mActivities)
		{
			copy = new ArrayList<BaseActivity>(mActivities);
		}
		if (copy.size() > 0)
		{
			return copy.get(copy.size() - 1);
		}
		return null;
	}

	public void exitApp()
	{
		finishAll();
		android.os.Process.killProcess(android.os.Process.myPid());
	}


	/**
	 * 显示dialog
	 */
	protected void showLoadDialog()
	{
		if (ct == null)
		{
			return;
		}
		if (isFinishing())
		{
			return;
		}
		PromptManager.showLoadDialog(this, "请求发送中...");
	}

	/**
	 * 隐藏dialog
	 */
	protected void dismissLoadDialog()
	{
		if (isFinishing())
		{
			return;
		}
		PromptManager.closeLoadingDialog();
	}
	/**
	 * 隐藏软键盘
	 * */
	public void hideSoftKeyBoard()
	{
		// 隐藏软键盘
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}
}
