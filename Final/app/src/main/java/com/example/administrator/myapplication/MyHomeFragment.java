package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyHomeFragment extends Fragment {
	
	public static final String TAG = "MyHomeFragment";

	private static MyHomeFragment myHomeFragmentInstance;
	
	public static MyHomeFragment getInstance(){
		if(myHomeFragmentInstance == null) {
			myHomeFragmentInstance = new MyHomeFragment();
		}
		return myHomeFragmentInstance;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_my_home, container, false);

	}
}
