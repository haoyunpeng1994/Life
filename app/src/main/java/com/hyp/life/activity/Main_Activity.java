package com.hyp.life.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyp.life.Constants;
import com.hyp.life.R;
import com.hyp.life.fragment.News_Fragment;
import com.hyp.life.fragment.Picture_Fragment;
import com.hyp.life.fragment.Setting_Fragment;
import com.hyp.life.fragment.Tool_Fragment;
import com.hyp.life.util.T;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by acer on 2015/11/8.
 */
public class Main_Activity extends ActionBarActivity implements View.OnClickListener {
    private long mExitTime;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private LinearLayout ll_container;
    private LinearLayout ll_xinxianshi,ll_wuliaotu,ll_qushezhi,ll_xiaiogongju,ll_user;
    DrawerLayout drawer;
    private  TextView   toolbar_title;
    private SystemBarTintManager tintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, Constants.bmobApplicationID);
//        initWindow();
        initView();
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.login_color));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         toolbar_title  = (TextView) findViewById(R.id.toolbar_title);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.abc_action_bar_home_description,R.string.abc_action_bar_home_description_format);

        actionBarDrawerToggle.syncState();

        drawer.setDrawerListener(actionBarDrawerToggle);

        ll_user = (LinearLayout) findViewById(R.id.ll_user);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ll_xinxianshi = (LinearLayout) findViewById(R.id.ll_xinxianshi);
        ll_wuliaotu = (LinearLayout) findViewById(R.id.ll_wuliaotu);
        ll_qushezhi = (LinearLayout) findViewById(R.id.ll_qushezhi);
        ll_xiaiogongju = (LinearLayout) findViewById(R.id.ll_xiaogongju);
        ll_xinxianshi.setOnClickListener(this);
        ll_wuliaotu.setOnClickListener(this);
        ll_qushezhi.setOnClickListener(this);
        ll_xiaiogongju.setOnClickListener(this);
        ll_user.setOnClickListener(this);
        ll_xinxianshi.performClick();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId())
        {
            case R.id.ll_xinxianshi:
                toolbar_title.setText("Life");
                fragmentTransaction.replace(R.id.ll_container, new News_Fragment());
                fragmentTransaction.commit();
                drawer.closeDrawers();
                break;
            case R.id.ll_wuliaotu:
                toolbar_title.setText("无聊图");
                fragmentTransaction.replace(R.id.ll_container,new Picture_Fragment());
                fragmentTransaction.commit();
                drawer.closeDrawers();
                break;
            case R.id.ll_xiaogongju:
                toolbar_title.setText("小工具");
                fragmentTransaction.replace(R.id.ll_container,new Tool_Fragment());
                fragmentTransaction.commit();
                drawer.closeDrawers();
                break;
            case R.id.ll_qushezhi:
                toolbar_title.setText("去设置");
                fragmentTransaction.replace(R.id.ll_container,new Setting_Fragment());
                fragmentTransaction.commit();
                drawer.closeDrawers();
                break;
            case R.id.ll_user:
                if(BmobUser.getCurrentUser(getApplicationContext())==null) {
                    T.showShort(getApplicationContext(), "登录注册");
                }else{
                    T.showShort(getApplicationContext(), "用户设置页面");
                }
                break;
        }
    }
}
