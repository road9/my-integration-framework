package com.lugq.mydemo.hzcframe;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabWidget;

import com.lugq.mydemo.hzcframe.fragment.FifthFragment;
import com.lugq.mydemo.hzcframe.fragment.FirstFragment;
import com.lugq.mydemo.hzcframe.fragment.FourthFragment;
import com.lugq.mydemo.hzcframe.fragment.SecondFragment;
import com.lugq.mydemo.hzcframe.fragment.ThirdFragment;

/**
 * 
 * @ClassName: MainActivity
 * @author lugq
 * @date 2014年9月3日 下午4:36:19
 *
 */
public class MainActivity extends FragmentActivity {
	
	private TabHost tabHost;
	
    private FirstFragment firstFragment;
    
    private SecondFragment secondFragment;
    
    private ThirdFragment thirdFragment;
    
    private FourthFragment fourthFragment;
    
    private FifthFragment fifthFragment;
    
    private final android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    
    private android.support.v4.app.FragmentTransaction mFragmentTrasaction;

    /**
     * every tabhost description.
     */
    private static final String[] FRAGMENT_DESC = {"first", "second", "third", "fourth", "fifth"};

	private RelativeLayout mTabIndicator1, mTabIndicator2, mTabIndicator3, mTabIndicator4, mTabIndicator5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initData();
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setupViews();
		init();
	
	}

	/**
	 * initialize object.
	 */
	private void initData() {
		mFragmentTrasaction = fm.beginTransaction();
	}
	
	/**
	 * setup tabhost layout.
	 */
	private void setupViews() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		LinearLayout layout = (LinearLayout) tabHost.getChildAt(0);
		TabWidget tw = (TabWidget) layout.getChildAt(1);
		
		//①.tab1
		mTabIndicator1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		//find imageview
		ImageView ivTab1 = (ImageView) mTabIndicator1.getChildAt(0);
		//modify the icoon
		ivTab1.setImageResource(android.R.drawable.ic_media_play);
		
		//②.tab2
		mTabIndicator2 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		//find imageview
		ImageView ivTab2 = (ImageView) mTabIndicator2.getChildAt(0);
		//modify the icoon
		ivTab2.setImageResource(android.R.drawable.sym_call_missed);
		
		//③.tab3
		mTabIndicator3 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		//find imageview
		ImageView ivTab3 = (ImageView) mTabIndicator3.getChildAt(0);
		//modify the icoon
		ivTab3.setImageResource(android.R.drawable.sym_call_missed);
		
		//④.tab4
		mTabIndicator4 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		//find imageview
		ImageView ivTab4 = (ImageView) mTabIndicator4.getChildAt(0);
		//modify the icoon
		ivTab4.setImageResource(android.R.drawable.sym_call_missed);
		
		//④.tab5
		mTabIndicator5 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		//find imageview
		ImageView ivTab5 = (ImageView) mTabIndicator5.getChildAt(0);
		//modify the icoon
		ivTab5.setImageResource(android.R.drawable.sym_call_missed);
	}
	
	/**
	 * initialize view
	 */
	private void init() {
		tabHost.setup();
		
		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				mFragmentTrasaction = fm.beginTransaction();
				
				//hall
				if(tabId.equalsIgnoreCase(FRAGMENT_DESC[0])) {
					hideLastPage();
					isTabFirst();
				}
				
				//two
				else if(tabId.equalsIgnoreCase(FRAGMENT_DESC[1])) {
					hideLastPage();
					isTabSecond();
				}
				
				//three
				else if(tabId.equalsIgnoreCase(FRAGMENT_DESC[2])) {
					hideLastPage();
					isTabThird();
				}
				
				//four
				else if(tabId.equalsIgnoreCase(FRAGMENT_DESC[3])) {
					hideLastPage();
					isTabFourth();
				}
				
				//five
				else if(tabId.equalsIgnoreCase(FRAGMENT_DESC[4])) {
					hideLastPage();
					isTabFifth();
				}
				
				mFragmentTrasaction.commitAllowingStateLoss();
			}
		};
		
		tabHost.setOnTabChangedListener(tabChangeListener);
		
		initTab();
		
		tabHost.setCurrentTab(0);
		
	}
	
	/**
	 * initialize tabhost.
	 */
	public void initTab() {
		
		TabHost.TabSpec tabSpecFirst = tabHost.newTabSpec(FRAGMENT_DESC[0]);
		tabSpecFirst.setIndicator(mTabIndicator1);
		tabSpecFirst.setContent(new TabContentFactory() {
			
			@Override
			public View createTabContent(String tag) {
				View v = new View(getBaseContext());
				return v;
			}
		});
		tabHost.addTab(tabSpecFirst);
		
		TabHost.TabSpec tabSpecSecond = tabHost.newTabSpec(FRAGMENT_DESC[1]);
		tabSpecSecond.setIndicator(mTabIndicator2);
		tabSpecSecond.setContent(new TabContentFactory() {
			
			@Override
			public View createTabContent(String tag) {
				View v = new View(getBaseContext());
				return v;
			}
		});
		tabHost.addTab(tabSpecSecond);
		
		TabHost.TabSpec tabSpecThird = tabHost.newTabSpec(FRAGMENT_DESC[2]);
		tabSpecThird.setIndicator(mTabIndicator3);
		tabSpecThird.setContent(new TabContentFactory() {
			
			@Override
			public View createTabContent(String tag) {
				View v = new View(getBaseContext());
				return v;
			}
		});
		tabHost.addTab(tabSpecThird);
		
		TabHost.TabSpec tabSpecFourth = tabHost.newTabSpec(FRAGMENT_DESC[3]);
		tabSpecFourth.setIndicator(mTabIndicator4);
		tabSpecFourth.setContent(new TabContentFactory() {
			
			@Override
			public View createTabContent(String tag) {
				View v = new View(getBaseContext());
				return v;
			}
		});
		tabHost.addTab(tabSpecFourth);
		
		TabHost.TabSpec tabSpecFifth = tabHost.newTabSpec(FRAGMENT_DESC[4]);
		tabSpecFifth.setIndicator(mTabIndicator5);
		tabSpecFifth.setContent(new TabContentFactory() {
			
			@Override
			public View createTabContent(String tag) {
				View v = new View(getBaseContext());
				return v;
			}
		});
		tabHost.addTab(tabSpecFifth);
		
		
		
	}
	
	/**
	 * show the FirstFragment.
	 */
	public void isTabFirst() {
		if (firstFragment == null) {
			firstFragment = new FirstFragment();
			mFragmentTrasaction.add(R.id.realtabcontent, firstFragment, FRAGMENT_DESC[0]);
			mFragmentTrasaction.addToBackStack(FRAGMENT_DESC[0]);
		} else {
			mFragmentTrasaction.show(firstFragment);
		}
	}
	
	/**
	 * show the SecondFragment.
	 */
	public void isTabSecond() {
		if (secondFragment == null) {
			secondFragment = new SecondFragment();
			mFragmentTrasaction.add(R.id.realtabcontent, secondFragment, FRAGMENT_DESC[1]);
			mFragmentTrasaction.addToBackStack(FRAGMENT_DESC[1]);
		} else {
			mFragmentTrasaction.show(secondFragment);
		}
	}
	
	/**
	 * show the ThirdFragment.
	 */
	public void isTabThird() {
		if (thirdFragment == null) {
			thirdFragment = new ThirdFragment();
			mFragmentTrasaction.add(R.id.realtabcontent, thirdFragment, FRAGMENT_DESC[2]);
			mFragmentTrasaction.addToBackStack(FRAGMENT_DESC[2]);
		} else {
			mFragmentTrasaction.show(thirdFragment);
		}
	}
	
	/**
	 * show the FourthFragment.
	 */
	public void isTabFourth() {
		if (fourthFragment == null) {
			fourthFragment = new FourthFragment();
			mFragmentTrasaction.add(R.id.realtabcontent, fourthFragment, FRAGMENT_DESC[3]);
			mFragmentTrasaction.addToBackStack(FRAGMENT_DESC[3]);
		} else {
			mFragmentTrasaction.show(fourthFragment);
		}
	}
	
	/**
	 * show the FifthFragment.
	 */
	public void isTabFifth() {
		if (fifthFragment == null) {
			fifthFragment = new FifthFragment();
			mFragmentTrasaction.add(R.id.realtabcontent, fifthFragment, FRAGMENT_DESC[4]);
			mFragmentTrasaction.addToBackStack(FRAGMENT_DESC[4]);
		} else {
			mFragmentTrasaction.show(fifthFragment);
		}
	}
	
	/**
	 * hide last fragment
	 */
	public void hideLastPage() {
		
		if(firstFragment != null) {
			mFragmentTrasaction.hide(firstFragment);
		}
		
		if(secondFragment != null) {
			mFragmentTrasaction.hide(secondFragment);
		}
		
		if(thirdFragment != null) {
			mFragmentTrasaction.hide(thirdFragment);
		}
		
		if(fourthFragment != null) {
			mFragmentTrasaction.hide(fourthFragment);
		}
		
		if(fifthFragment != null) {
			mFragmentTrasaction.hide(fifthFragment);
		}
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			System.exit(0);
			return true;
		}
		return false;
	}
	
}



















