package com.example.accelerator.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioGroup.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressLint("HandlerLeak") public class MessageFragment extends Fragment implements OnClickListener, OnPageChangeListener {

	public static final String TAG = "MessageFragment";
	private static final int WHAT_SET_CURSOR_POSITION = 0;
	
	private static MessageFragment msgeFragInstance;
	
	private LayoutInflater inflater;
	
	private View msgFragLayout, cursorView;
	private ViewPager viewPager;
	
	private int titleTxtWidth;
	private int cursorWidth, cursorHeight = 10;
	private int curCursorPos, lastCursorPos;
	
	private ArrayList<View> childViewsOfViewPager = new ArrayList<View>();	
	private Handler handler;
	private Runnable cursorPosRunnable;
	
	public static MessageFragment getInstance(){
		if(msgeFragInstance == null) {
			msgeFragInstance = new MessageFragment();
		}
		return msgeFragInstance;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		this.inflater = inflater;
		msgFragLayout = inflater.inflate(R.layout.fragment_message, container, false);
		
		initHandler();
		initCursorPositionRunnable();
		
		initMsgTitle();
		initCursor();
		initViewPager();
		
		return msgFragLayout;
	}

	private void initMsgTitle() {
		TextView msgTxt = 
			(TextView) msgFragLayout.findViewById(R.id.message_title_msg);
		TextView conversationTxt = 
			(TextView) msgFragLayout.findViewById(R.id.message_title_conversation);
		TextView videoTxt = 
			(TextView) msgFragLayout.findViewById(R.id.message_title_video);
		
		msgTxt.setOnClickListener(this);
		conversationTxt.setOnClickListener(this);
		videoTxt.setOnClickListener(this);
		
		titleTxtWidth = getTitleTextViewWidth();
	}

	private void initCursor() {
		
		cursorView = msgFragLayout.findViewById(R.id.cursor_MessageFragment);
		
		cursorWidth = getCursorWidth();
		LayoutParams params = new LayoutParams(cursorWidth, cursorHeight);
		
		cursorView.setLayoutParams(params);
		setCursorPosition(curCursorPos);
	}
	
	private void initViewPager() {
		
		viewPager = 
			(ViewPager) msgFragLayout.findViewById(R.id.viewPager_MessageFragment);
		
		View msgView = inflater.inflate(R.layout.viewpager_message_msg, null);
		View conversationView = 
				inflater.inflate(R.layout.viewpager_message_conversation, null);
		View videoView = inflater.inflate(R.layout.viewpager_message_video, null);
	
		childViewsOfViewPager.add(msgView);
		childViewsOfViewPager.add(conversationView);
		childViewsOfViewPager.add(videoView);
		
		viewPager.setAdapter(new ViewPagerAdapter(childViewsOfViewPager));
		viewPager.setOnPageChangeListener(this);
		
	}
	
	private void setCursorPosition(int position) {
		lastCursorPos = curCursorPos;
		curCursorPos = position;
		new Thread(cursorPosRunnable).start();
	}
	
	private int getTitleTextViewWidth() {
		DisplayMetrics outMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int screenWidth = outMetrics.widthPixels;
		return screenWidth/3;
	}
	private int getCursorWidth() {
		
		return titleTxtWidth/3;
	}

	private void initHandler() {
		
		handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MessageFragment.WHAT_SET_CURSOR_POSITION:
					
					cursorView.setX(msg.arg1);
					break;

				default:
					break;
				}
				
			};
		};
		
	}

	private void initCursorPositionRunnable() {

		cursorPosRunnable = new Runnable() {
			
			@Override
			public void run() {
				calcXAndSendMsg();
			}

			private void calcXAndSendMsg() {
				try{
					int cusorCurrentX = titleTxtWidth*lastCursorPos+(titleTxtWidth-cursorWidth)/2;
					int cursorTargetX = titleTxtWidth*curCursorPos+(titleTxtWidth-cursorWidth)/2;
					
					if(lastCursorPos == curCursorPos) {
						handler.obtainMessage(WHAT_SET_CURSOR_POSITION, cusorCurrentX, 0).sendToTarget();
					} else {
						while(cusorCurrentX != cursorTargetX) {
							
							if(cusorCurrentX < cursorTargetX) {
								cusorCurrentX+=15;
							} else {
								cusorCurrentX-=15;
							}
							handler.obtainMessage(WHAT_SET_CURSOR_POSITION, cusorCurrentX, 0).sendToTarget();
							Thread.sleep(5);
						}
					}
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.message_title_msg:
			viewPager.setCurrentItem(0);
			break;
		case R.id.message_title_conversation:
			viewPager.setCurrentItem(1);
			break;
		case R.id.message_title_video:
			viewPager.setCurrentItem(2);
			break;
		default:
			break;
		}
	}


	@Override
	public void onPageScrollStateChanged(int state) {
		
	}
	int offset;
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}


	@Override
	public void onPageSelected(int position) {
		setCursorPosition(position);
	}
	
}
