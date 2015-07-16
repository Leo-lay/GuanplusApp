package com.youxu.client;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.guanplus.MainActivity;
import com.guanplus.R;
import com.guanplus.manager.BaseApplication;
import com.guanplus.utils.ContentValue;
import com.guanplus.utils.LogUtils;
import com.guanplus.utils.SharedPreferencesUtils;
import com.guanplus.utils.StringUtils;
import com.igexin.sdk.PushManager;

public class SplashActivity extends Activity implements OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 初始化推送(不建议在Application继承类中调用。)
        PushManager.getInstance().initialize(this.getApplicationContext());

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_splash);
        init();

    }

    private void init()
    {
        // creatShortCut();
        // System.out.println(BaseApplication.getApplication().getConfigInfoBoolean("user.isFirstLogin"));
        LogUtils.d("token@@" + BaseApplication.getApplication().getConfigInfo(ContentValue.USER_CONFIG_TOKEN));
        String security = BaseApplication.getApplication().getConfigInfo(ContentValue.USER_CONFIG_SECURITY);
        // 初期不做分路
        // 后续加上登录跟安全码分路
        startActivity(new Intent(this, MainActivity.class));
        if (StringUtils.isEmpty(security)|| SharedPreferencesUtils.getBoolean(this, "skip_security", false))
        {
           // intent = new Intent(this, LoginActivity.class); // 如果没有设置安全码，跳到登录
        }
        else
        {
            //intent = new Intent(this, SecurityCodeActivity.class); // 如果不为第一次登录状态，则跳转到输入安全码界面
         //   intent.putExtra("flag", ContentValue.SECURITYCODE_ENTER);
        }
        //startActivity(intent);
    }
    /** 有待完善 */
    @Deprecated
    private void creatShortCut()
    {
        if (SharedPreferencesUtils.getBoolean(this, "firstEnter", true))
        {
            Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            // 1 需要知道快捷方式 要做什么事
            Intent value = new Intent();
            value.setAction(Intent.ACTION_MAIN);
            value.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("duplicate", false);
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, value);
            // 2 快捷方式的名字
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));
            // 3 快捷方式图标
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(getResources(), R.drawable.logo));
            sendBroadcast(intent);
            SharedPreferencesUtils.saveBoolean(this, "firstEnter", false);
        }
    }

    @Override
    public void onClick(View v)
    {

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        finish();
    }
}
