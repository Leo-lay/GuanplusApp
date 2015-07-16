package com.guanplus.recevier;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.guanplus.R;
import com.guanplus.bean.PushBean;
import com.guanplus.manager.BaseApplication;
import com.guanplus.utils.CommonUtil;
import com.guanplus.utils.ContentValue;
import com.guanplus.utils.LogUtils;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 推送Receiver
 * 
 * @author Scleo 2015/7/15
 * 
 */
public class PushReceiver extends BroadcastReceiver
{
	private int	unread	= 0;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();
		LogUtils.d("PushReceiver，获取到action=" + bundle.getInt("action"));
		switch (bundle.getInt(PushConsts.CMD_ACTION))
		{
			case PushConsts.GET_MSG_DATA:
				// 获取透传（payload）数据
				byte[] payload = bundle.getByteArray("payload");
				if (payload != null)
				{
					String data = new String(payload);
					LogUtils.d("推送返回的结果：" + data);
					Type type = new TypeToken<List<PushBean>>()
					{
					}.getType();
					// PushBean pushBean = CommonUtil.json2Bean(data,
					// PushBean.class);
					List<PushBean> pushData = CommonUtil.json2List(data, type);
					if (pushData == null)
						return;
					unread += pushData.size();
					if (unread == 1)
					{
						showNotification(context, pushData.get(0));
					}
					/*
					 * for (PushBean pushBean : pushData)
					 * {
					 * showNotification(context, pushBean);
					 * }
					 */
					LogUtils.d("PushReceiver，获取到data=" + data);
					// TODO:接收处理透传（payload）数据
				}
				break;
			case PushConsts.GET_CLIENTID:
				// 获取ClientID(CID)
				String cid = bundle.getString("clientid");
				LogUtils.d("PushReceiver，获取到ClientID:" + cid);

				if (BaseApplication.getApplication().isLogin())
				{
					String userid = BaseApplication.getApplication().getUserInfo().getId();
					userid = userid.replaceAll("-", "_");
					LogUtils.d("PushReceiver，已登录，开始绑定用户名为别名：" + userid);
					PushManager.getInstance().bindAlias(context, userid);
				}
				else
				{
					LogUtils.d("PushReceiver，未登录，无法绑定别名");
				}
				// 推送：生成客户端ID不方便，将用户名clientID绑定，后台根据这个clientID。
				// TODO:
				/*
				 * 第三方应用需要将ClientID上传到第三方服务器，并且将当前用户帐号和ClientID进行关联，
				 * 以便以后通过用户帐号查找ClientID进行消息推送。有些情况下ClientID可能会发生变化，为保证获取最新的ClientID
				 * ，
				 * 请应用程序在每次获取ClientID广播后，都能进行一次关联绑定
				 */
				break;
			// 添加其他case
			// .........
			default:
				break;
		}
	}

	/**
	 * 在状态栏显示通知
	 */
	private void showNotification(Context ctx, PushBean pushBean)
	{
		// 创建一个NotificationManager的引用
		NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		// 定义Notification的各种属性
		Notification notification = new Notification(R.drawable.logo, ctx.getString(R.string.app_name), System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL; //
		// notification.contentView = new RemoteViews(packageName, layoutId)
		Intent notificationIntent = null; // 点击该通知后要跳转的Activity
		LogUtils.w("点击的数据：" + pushBean.toString());
		/*switch (pushBean.getEntityType())
		{
			case 0:
			case 1:
				notificationIntent = new Intent(ctx, ShowBillActivity.class);
				break;
			case 2:
				notificationIntent = new Intent(ctx, ShowReimbursementActivity.class);
			case 3:
				notificationIntent = new Intent(ctx, ShowPettycashActivity.class);
				break;
			case 4:
				notificationIntent = new Intent(ctx, ShowPaymentActivity.class);
				break;
			case 5:
				notificationIntent = new Intent(ctx, ShowVoucherActivity.class);
				break;
		}*/
		if (notificationIntent == null)
			return;
		LogUtils.w("接收到的ID" + pushBean.getId());
		notificationIntent.putExtra(ContentValue.INTENT_ID, pushBean.getId());
		notificationIntent.putExtra(ContentValue.INTENT_ENTITY_TYPE, pushBean.getEntityType());
	
		PendingIntent contentItent = PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	
		notification.setLatestEventInfo(ctx, "管家提醒", "您收到一条未读信息,点击查看详情", contentItent);

		// 把Notification传递给NotificationManager
		notificationManager.notify(0, notification);
	}
}
