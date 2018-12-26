package com.example.accelerator.myapplication;

//import com.example.accelerator.myapplication.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener{

	FragmentManager fragmentManager = getSupportFragmentManager();
	
	MessageFragment messageFrament = (MessageFragment) MessageFragment.getInstance();
	FriendFragment friendFragment = (FriendFragment) FriendFragment.getInstance();
	FeedFragment feedFragment = (FeedFragment) FeedFragment.getInstance();
	MyHomeFragment myHomeFragment = (MyHomeFragment) MyHomeFragment.getInstance();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        showDefaultFragment();
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
