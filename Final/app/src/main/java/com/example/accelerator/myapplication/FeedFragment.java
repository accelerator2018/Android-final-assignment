package com.example.accelerator.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FeedFragment extends Fragment {
	
	public static final String TAG = "FeedFragment";

	private static FeedFragment feedFragmentInstance;
	
	public static FeedFragment getInstance(){
		if(feedFragmentInstance == null) {
			feedFragmentInstance = new FeedFragment();
		}
		return feedFragmentInstance;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_feed, container, false);

	}
}
