package com.example.yanxu.myproject.guide;

import java.util.ArrayList;
import java.util.List;

import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.guide.fragment.Fragment1;
import com.example.yanxu.myproject.guide.fragment.Fragment2;
import com.example.yanxu.myproject.guide.fragment.Fragment3;
import com.example.yanxu.myproject.guide.fragment.Fragment4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import android.view.Window;
/**
 * ViewPager 引导
 *
 */
public class ViewPagerActivity1 extends FragmentActivity {
	private ViewPager mVPActivity;
	private Fragment1 mFragment1;
	private Fragment2 mFragment2;
	private Fragment3 mFragment3;
	private Fragment4 mFragment4;
	private List<Fragment> mListFragment = new ArrayList<Fragment>();
	private PagerAdapter mPgAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_viewpager1);
		initView();
	}

	private void initView() {
		mVPActivity = (ViewPager) findViewById(R.id.vp_activity);
		mFragment1 = new Fragment1();
		mFragment2 = new Fragment2();
		mFragment3 = new Fragment3();
		mFragment4 = new Fragment4();
		mListFragment.add(mFragment1);
		mListFragment.add(mFragment2);
		mListFragment.add(mFragment3);
		mListFragment.add(mFragment4);
		/**
		 * 传递两个参数
		 */
		mPgAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
				mListFragment);
		mVPActivity.setAdapter(mPgAdapter);
//		mVPActivity.setPageTransformer(true, new RotateDownPageTransformer());
		mVPActivity.setPageTransformer(false, new PageTransformer() {
			
			@Override
			public void transformPage(View page, float position) {
				final float normalizedposition = Math.abs(Math.abs(position) - 1);
			    page.setScaleX(normalizedposition / 2 + 0.5f);
			    page.setScaleY(normalizedposition / 2 + 0.5f);
			}
		});
	}
}


