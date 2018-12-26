package com.example.accelerator.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FriendFragment extends Fragment {
	
	public static final String TAG = "FriendFragment";
	
	private static FriendFragment friendFragmentInstance;
	
	public static FriendFragment getInstance(){
		if(friendFragmentInstance == null) {
			friendFragmentInstance = new FriendFragment();
		}
		return friendFragmentInstance;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_friend, container, false);

	}
}
