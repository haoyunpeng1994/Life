package com.hyp.life.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hyp.life.Constants;
import com.hyp.life.R;
import com.hyp.life.util.DensityUtils;
import com.hyp.life.util.SPUtils;


/**
 * Created by acer on 2015/11/7.
 */
public class Guide_Actvity extends Activity {
    private ViewPager guide_viewpager;
    private LinearLayout ll_point;
    private Button bt_guide;
    private View v_point;
    private int[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        SPUtils.put(getApplicationContext(), Constants.spKeyIsFirst,false);
        initView();
    }

    private void initView() {
        guide_viewpager = (ViewPager) findViewById(R.id.guide_viewpager);
        bt_guide = (Button) findViewById(R.id.bt_guide);
        bt_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guide_Actvity.this,Main_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        v_point = findViewById(R.id.v_point);
        ll_point = (LinearLayout) findViewById(R.id.ll_point);
        initData();
        guide_viewpager.setAdapter(new myAdapter());
        guide_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==image.length-2&&positionOffset<0.6f)
                {
                    bt_guide.setVisibility(View.GONE);
                }else if(position==image.length-1)
                {
                    bt_guide.setVisibility(View.VISIBLE);
                }
                int pointMoveX = (int) ((position+positionOffset)*DensityUtils.dp2px(getApplicationContext(),30f));
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v_point.getLayoutParams();
                params.leftMargin = pointMoveX;
                v_point.setLayoutParams(params);
            }
            @Override
            public void onPageSelected(int position) {
                if(position==3)
                {
                    bt_guide.setVisibility(View.VISIBLE);
                }else
                {
                    bt_guide.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initData() {
        image = new int[]{R.mipmap.guid_1, R.mipmap.guid_2, R.mipmap.guid_3
                , R.mipmap.guid_4};
        //添加灰点
        for(int i =0;i<image.length;i++)
        {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.guide_point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dp2px(getApplicationContext(),15f),DensityUtils.dp2px(getApplicationContext(),15f));
            if(i!=0)
            {
                params.leftMargin=DensityUtils.dp2px(getApplicationContext(),15f);
            }
            view.setLayoutParams(params);
            ll_point.addView(view);
        }
    }
    class myAdapter extends PagerAdapter
    {
        @Override
        public int getCount() {
            return image.length;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(getApplicationContext(),R.layout.adapter_viewpager_guide,null);
            ImageView iv_pager = (ImageView) view.findViewById(R.id.iv_pager);
            iv_pager.setImageResource(image[position]);
            container.addView(view);
            return view;
        }
    }
}
