package com.guanplus.manager;


import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;

import com.guanplus.bean.User;
import com.guanplus.utils.CommonUtil;
import com.guanplus.utils.ContentValue;
import com.guanplus.utils.SharedPreferencesUtils;
import com.guanplus.utils.StringUtils;

import java.io.File;
import java.io.FileOutputStream;


/**
 * @author Scleo
 */
public class BaseApplication extends Application
{
	/** 网络标示 **/
	public static final int			NETTYPE_WIFI	= 0x01;
	public static final int			NETTYPE_CMWAP	= 0x02;
	public static final int			NETTYPE_CMNET	= 0x03;
	private String					userid			= "";
	// private boolean isLogin = false;
	// private boolean isFirstLogin = true;
	/** 全局Context */
	private static BaseApplication	mInstance;
	/** 主线程ID */
	private static int				mMainThreadId	= -1;
	/** 主线程ID */
	private static Thread			mMainThread;
	/** 主线程Handler */
	private static Handler			mMainThreadHandler;
	/** 主线程Looper */
	private static Looper			mMainLooper;

	/** 测试附件添加图片功能 */

	@Override
	public void onCreate()
	{
		// LogUtils.d("application启动�?");
		mMainThreadId = android.os.Process.myTid();
		mMainThread = Thread.currentThread();
		mMainThreadHandler = new Handler();
		mMainLooper = getMainLooper();
		mInstance = this;
		setConfingInfo("user.isFirstLogin", true);
		super.onCreate();
		initImgLoader();

		ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);  
		int heapSize = manager.getMemoryClass();  
		//注册全局异常捕获对象
		//Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionManager.getInstanceHandler());
	}

	/**
	 * 初始化ImageLoader参数
	 * */
	private void initImgLoader()
	{

		/*
			换成volley的东西
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT).bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设�?
				// 如Bitmap.Config.ARGB_8888
				.showImageOnLoading(R.drawable.icon_loading_img) // 默认图片
				.showImageForEmptyUri(R.drawable.icon_loading_img) // url爲空會显示该图片，自己放在drawable里面�?
				.showImageOnFail(R.drawable.icon_loading_img)// 加载失败显示的图�?
				// .displayer(new RoundedBitmapDisplayer(5)) // 圆角，不�?要请删除
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).memoryCacheExtraOptions(480, 800)// 缓存在内存的图片的宽和高�?
				.memoryCache(new WeakMemoryCache()).memoryCacheSize(2 * 1024 * 1024) // 缓存到内存的�?大数�?
				.discCacheSize(50 * 1024 * 1024) // 缓存到文件的�?大数�?
				.discCacheFileCount(1000) // 文件数量
				.defaultDisplayImageOptions(options) // 上面的options对象，一些属性配�?
				.build();
		ImageLoader.getInstance().init(config); // 初始�?*/
	}

	public static BaseApplication getApplication()
	{
		return mInstance;
	}

	/** 获取主线程ID */
	public static int getMainThreadId()
	{
		return mMainThreadId;
	}

	/** 获取主线�? */
	public static Thread getMainThread()
	{
		return mMainThread;
	}

	/** 获取主线程的handler */
	public static Handler getMainThreadHandler()
	{
		return mMainThreadHandler;
	}

	/** 获取主线程的looper */
	public static Looper getMainThreadLooper()
	{
		return mMainLooper;
	}

	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
	}

	/**
	 * �?测网络是否可�?
	 * 
	 * @return
	 */
	public boolean isNetworkConnected()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 获取当前网络类型
	 * 
	 * @return 0：没有网�? 1：WIFI网络 2：WAP网络 3：NET网络
	 */
	public int getNetworkType()
	{
		int type = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null)
		{
			return type;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE)
		{
			String extraInfo = networkInfo.getExtraInfo();
			if (!StringUtils.isEmpty(extraInfo))
			{
				if (extraInfo.toLowerCase().equals("cmnet"))
				{
					type = NETTYPE_CMNET;
				}
				else
				{
					type = NETTYPE_CMWAP;
				}
			}
		}
		else if (nType == ConnectivityManager.TYPE_WIFI)
		{
			type = NETTYPE_WIFI;
		}
		return type;
	}

	/** 将图片保存到SDCard�? */
	public void storeImageToSDCARD(Bitmap colorImage, String ImageName, String path)
	{
		File imagefile = new File(path + File.separator + ImageName);
		if (!imagefile.exists())
		{
			try
			{
				imagefile.createNewFile();
				FileOutputStream fos = new FileOutputStream(imagefile);
				colorImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.flush();
				fos.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取用户Id
	 * */
	public String getUserid()
	{
		return userid;
	}

	/**
	 * 判断是否第一次登�?
	 * 
	 * @param user
	 */
	public boolean isFirstLogin()
	{
		return BaseApplication.getApplication().getConfigInfoBoolean("user.isFirstLogin");
	}

	/**
	 * 判断用户是否登录
	 * */
	public boolean isLogin()
	{
		return BaseApplication.getApplication().getConfigInfoBoolean("user.isLogin");
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	public void saveUserInfo(User user)
	{
		this.userid = user.getId();
		setConfingInfo("user.isLogin", true);
		setConfingInfo(ContentValue.USER_CONFIG_PASSWORD, user.getPassword());
		setConfingInfo(ContentValue.USER_CONFIG_ID, user.getId());
		setConfingInfo(ContentValue.USER_CONFIG_ROLES, user.getRoles());
		setConfingInfo(ContentValue.USER_CONFIG_TOKEN, user.getAccessToken());
		setConfingInfo(ContentValue.USER_CONFIG_NAME, user.getDisplayName());
		setConfingInfo(ContentValue.USER_CONFIG_PHONE, user.getPhoneNumber());
		setConfingInfo(ContentValue.USER_CONFIG_EMAIL, user.getEmail());
		setConfingInfo(ContentValue.USER_CONFIG_LASTLOGINTIME, String.valueOf(CommonUtil.getCurrentTime()));
		setConfingInfo(ContentValue.USER_CONFIG_AVATAR, user.getAccountPhotoUrl());
		
	}

	/**
	 * 清空用户信息
	 * 
	 */
	public void clearUserInfo()
	{
		this.userid = "";
		setConfingInfo(ContentValue.USER_CONFIG_PASSWORD, "");
		setConfingInfo(ContentValue.USER_CONFIG_ID, "");
		setConfingInfo(ContentValue.USER_CONFIG_ROLES,"");
		setConfingInfo(ContentValue.USER_CONFIG_TOKEN, "");
		setConfingInfo(ContentValue.USER_CONFIG_NAME, "");
		setConfingInfo(ContentValue.USER_CONFIG_PHONE,"");
		setConfingInfo(ContentValue.USER_CONFIG_EMAIL, "");
		setConfingInfo(ContentValue.USER_CONFIG_LASTLOGINTIME, "");
		setConfingInfo(ContentValue.USER_CONFIG_AVATAR, "");
		setConfingInfo(ContentValue.USER_CONFIG_ISLOGIN, false);
		setConfingInfo(ContentValue.USER_CONFIG_SECURITY, "");
		setConfingInfo(ContentValue.USER_CONFIG_ISFIRSTLOGIN, true);
		
	}

	/**
	 * 保存4位安全码
	 * 
	 * @param user
	 */
	public void saveSecurityCode(String string)
	{
		setConfingInfo(ContentValue.USER_CONFIG_SECURITY, string);
	}


	/**
	 * 设置字符串类型参�?
	 * */
	public void setConfingInfo(String key, String value)
	{
		SharedPreferencesUtils.saveString(this, key, value);
	}

	/**
	 * 设置布尔类型参数
	 * */
	public void setConfingInfo(String key, boolean value)
	{
		SharedPreferencesUtils.saveBoolean(this, key, value);
	}

	/**
	 * 获取登录用户信息
	 * */
	public User getUserInfo()
	{
		User ui = new User();
		ui.setAccountPhotoUrl(getConfigInfo(ContentValue.USER_CONFIG_AVATAR));
		ui.setId(getConfigInfo(ContentValue.USER_CONFIG_ID));
		ui.setRoles(getConfigInfo(ContentValue.USER_CONFIG_ROLES));
		ui.setAccessToken(getConfigInfo(ContentValue.USER_CONFIG_TOKEN));
		ui.setDisplayName(getConfigInfo(ContentValue.USER_CONFIG_NAME));
		ui.setPhoneNumber(getConfigInfo(ContentValue.USER_CONFIG_PHONE));
		ui.setEmail(getConfigInfo(ContentValue.USER_CONFIG_EMAIL));
		return ui;
	}

	/**
	 * 获取字符串类型配置文件内�?
	 * */
	public String getConfigInfo(String key)
	{
		return SharedPreferencesUtils.getString(this, key, "");
	}

	/**
	 * 获取布尔类型配置文件内容
	 * */
	public boolean getConfigInfoBoolean(String key)
	{
		return SharedPreferencesUtils.getBoolean(this, key, false);
	}
	
	/**
	 * 检测当前设备用户ID是否�?�?
	 * */
	public  boolean checkApprove(String createrId){
		if(StringUtils.isEmpty(createrId)){
			return false;
		}
		String currId = getConfigInfo(ContentValue.USER_CONFIG_ID);
		if(createrId.equals(currId)){
			return true;
		}
		return false;
	}

}
