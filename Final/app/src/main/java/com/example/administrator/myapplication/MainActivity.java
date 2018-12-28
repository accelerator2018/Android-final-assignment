package com.example.administrator.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.administrator.myapplication.bean.Info;
import com.example.administrator.myapplication.db.SchoolDAO;
import com.example.administrator.myapplication.db.Service;

import org.greenrobot.eventbus.EventBus;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private java.util.List<Info> sList = null;


	FragmentManager fragmentManager = getSupportFragmentManager();
	
	MessageFragment messageFrament = (MessageFragment) MessageFragment.getInstance();
	FriendFragment friendFragment = (FriendFragment) FriendFragment.getInstance();
	FeedFragment feedFragment = (FeedFragment) FeedFragment.getInstance();
	MyHomeFragment myHomeFragment = (MyHomeFragment) MyHomeFragment.getInstance();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
		initData();
		
        initViews();
        showDefaultFragment();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}



	private void initData() {
		Service service = new Service(this);

		SchoolDAO studentDAO = new SchoolDAO(this);
		SharedPreferences preferences = getSharedPreferences("contact", Context.MODE_PRIVATE);
		boolean first = preferences.getBoolean("first", true);
		if (first) {
			try {
				sList = service.parserXml("text.xml");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			String[] strStudent = new String[sList.size()];

			int i = 0;
			for (Info student : sList) {
				strStudent[i] = student.getId() + student.getName()
						+ student.getTime();

				studentDAO.addContentValues(student);
				Log.e("Tag", "---------student.getName()-" + student.getName());
				i++;
			}
		}
	}

	private void initViews() {
    	
    	TextView messageTv = (TextView) findViewById(R.id.footer_message);
    	TextView friendTv = (TextView) findViewById(R.id.footer_friend);
    	TextView feedTv = (TextView) findViewById(R.id.footer_feed);
    	TextView myHomeTv = (TextView) findViewById(R.id.footer_my_home);
		
    	messageTv.setOnClickListener(this);
    	friendTv.setOnClickListener(this);
    	feedTv.setOnClickListener(this);
    	myHomeTv.setOnClickListener(this);
    }

	 private void showDefaultFragment() {

		 switchFragment(R.id.fragment_container_MainActivity,
					messageFrament, MessageFragment.TAG);
	     
	}
	 
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.footer_message:
			switchFragment(R.id.fragment_container_MainActivity,
					messageFrament, MessageFragment.TAG);
			break;
		case R.id.footer_friend:
			switchFragment(R.id.fragment_container_MainActivity,
					friendFragment, FriendFragment.TAG);
			break;
		case R.id.footer_feed:
			switchFragment(R.id.fragment_container_MainActivity,
					feedFragment, FeedFragment.TAG);
			break;
		case R.id.footer_my_home:
			switchFragment(R.id.fragment_container_MainActivity,
					myHomeFragment, MyHomeFragment.TAG);
			break;
		default:
			break;
		}
	}

	public void refreshMessageFragment() {
		messageFrament.refresh();
	}
    
	private void switchFragment(int id, Fragment fragment,
			String tag) {
		
		fragmentManager.beginTransaction().
		replace(id, fragment, tag).commit();
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		System.exit(0);
	}
}
