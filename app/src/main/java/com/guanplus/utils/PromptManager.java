package com.guanplus.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



/**
 * 
 * @author Scleo
 * 
 */

public class PromptManager
{
	private static Dialog	dialog;
	private ProgressDialog pd;
	@SuppressWarnings("deprecation")
	public static void showLoadDialog(Context context, String msg)
	{
		/*LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.llLoaddialog);// 加载布局
		// main.xml中的ImageView
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.imgLoaddialog);
		TextView tipTextView = (TextView) v.findViewById(R.id.tvLoadMsg);// 提示文字
		// 加载动画
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_dialog);
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(msg);// 设置加载信息

		dialog= new Dialog(context, R.style.custom_loading_dialog);// 创建自定义样式dialog
		dialog.setCancelable(false);// 不可以用“返回键”取�?
		dialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
		dialog.show();*/
	}

	public static void closeLoadingDialog()
	{
		if (dialog != null && dialog.isShowing())
		{
			dialog.dismiss();
		}
	}

	/**
	 * 当判断当前手机没有网络时使用
	 * 
	 * @param context
	 */
	public static void showNoNetWork(final Context context)
	{
		/*Builder builder = new Builder(context);
		builder.setIcon(R.drawable.ic_launcher)//
				.setTitle(R.string.app_name)//
				.setMessage("当前无网�?").setPositiveButton("设置", new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// 跳转到系统的网络设置界面
						Intent intent = new Intent();
						intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
						context.startActivity(intent);

					}
				}).setNegativeButton("知道�?", null).show();*/
	}

	/**
	 * �?出系�?
	 * 
	 * @param context
	 */
	public static void showExitSystem(Context context)
	{
		/*Builder builder = new Builder(context);
		builder.setIcon(R.drawable.ic_launcher)//
				.setTitle(R.string.app_name)//
				.setMessage("是否�?出应�?").setPositiveButton("确定", new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						android.os.Process.killProcess(android.os.Process.myPid());
						// 多个Activity—�??
						// 将所有用到的Activity都存起来，获取全部，干掉
						// BaseActivity—�?�onCreated—�?�放到容器中
					}
				})//
				.setNegativeButton("取消", null)//
				.show();*/

	}

	/**
	 * 显示错误提示�?
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showErrorDialog(Context context, String msg)
	{/*
		new Builder(context)//
				.setIcon(R.drawable.ic_launcher)//
				.setTitle(R.string.app_name)//
				.setMessage(msg)//
				.setNegativeButton("test", null)//
				.show();*/
	}

	public static void showToast(Context context, String msg)
	{
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, int msgResId)
	{
		Toast.makeText(context, msgResId, Toast.LENGTH_SHORT).show();
	}

	// 当测试阶段时true
	private static final boolean	isShow	= true;

	/**
	 * 测试�? 在正式投入市场：�?
	 * @param context
	 * @param msg
	 */
	public static void showToastTest(Context context, String msg)
	{
		if (isShow)
		{
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}
}
