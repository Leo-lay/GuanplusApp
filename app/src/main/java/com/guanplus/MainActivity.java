package com.guanplus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.guanplus.fragment.HomeFragment;
import com.guanplus.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {
    private  LeftMenuFragment mLfFragment;
    private   SlidingMenu mSm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 侧边栏界面
        setBehindContentView(R.layout.slide_behind);

        setContentView(R.layout.activity_main);
        mSm = getSlidingMenu();

        //设置侧滑方向
        mSm.setMode(SlidingMenu.LEFT);
        // 设置侧滑以后，剩余显示部分
        mSm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置为屏幕边沿滑动
        mSm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置阴影
        mSm.setShadowDrawable(R.drawable.shadow);
        // 设置阴影的宽度
        mSm.setShadowWidthRes(R.dimen.shadow_width);
       // Gs

        mLfFragment = new LeftMenuFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.slide_left, mLfFragment, "MenuFrament").commit();

        switchFrag(new HomeFragment());
    }

    public void switchFrag(Fragment frag){
            getSupportFragmentManager().beginTransaction().replace(R.id.content,frag).commit();
            if(mSm.isMenuShowing())
                mSm.toggle();
    }
}
