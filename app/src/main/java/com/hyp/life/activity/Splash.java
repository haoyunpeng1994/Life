package com.hyp.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyp.life.Constants;
import com.hyp.life.R;
import com.hyp.life.util.AppUtils;
import com.hyp.life.util.SPUtils;

import java.math.RoundingMode;

import cn.bmob.v3.BmobUser;

/**
 * Splash界面
 */
public class Splash extends Activity {
    private RelativeLayout rl_splash;
    private TextView tv_splash_cong,tv_splash_heart,tv_splash_kai,tv_splash_shi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initView();
    }
    public void initView() {
        tv_splash_heart = (TextView) findViewById(R.id.tv_splash_heart);
        TranslateAnimation XtranslateAnimation = new TranslateAnimation(-300,0,0,0);
        XtranslateAnimation.setDuration(1500);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(XtranslateAnimation);
        tv_splash_heart.setAnimation(animationSet);
        XtranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 2f, 0.0f, 2f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(1000);//设置动画持续时间
                scaleAnimation.setRepeatCount(1);//设置重复次数
                scaleAnimation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
                tv_splash_heart.setAnimation(scaleAnimation);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        boolean isFirst = (boolean) SPUtils.get(getApplicationContext(), Constants.spKeyIsFirst, true);
                        Intent intent;
                        if (isFirst) {
                            intent = new Intent(Splash.this, Guide_Actvity.class);
                        } else {
                            intent = new Intent(Splash.this, Main_Activity.class);
                        }
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);
        rl_splash.setAnimation(alphaAnimation);

        TextView tv_version = (TextView) findViewById(R.id.tv_version);
        String versionName = AppUtils.getVersionName(getApplicationContext());
        tv_version.setText(versionName);
    }
}
