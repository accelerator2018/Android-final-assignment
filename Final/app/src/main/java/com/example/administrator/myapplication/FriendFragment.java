package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.bean.Info;
import com.example.administrator.myapplication.db.SchoolDAO;

import org.greenrobot.eventbus.EventBus;

public class FriendFragment extends Fragment {
	
	public static final String TAG = "FriendFragment";

	public TextView add;
	public EditText editText;
	SchoolDAO schoolDAO;
	
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
		View inflate = inflater.inflate(R.layout.fragment_friend, container, false);

		editText = inflate.findViewById(R.id.tv_belong);
		add = inflate.findViewById(R.id.bt_add);

		schoolDAO = new SchoolDAO(getContext());

		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String key = editText.getText().toString().trim();
				if (!TextUtils.isEmpty(key)) {
					Info info = new Info(System.currentTimeMillis() + "", key, System.currentTimeMillis() + "");
					schoolDAO.add(info);
					Toast.makeText(getContext(), "添加成功",Toast.LENGTH_SHORT).show();
					editText.setText("");
					EventBus.getDefault().post(info);
					Log.e("TAG","----System.currentTimeMillis()-" +System.currentTimeMillis());
					((MainActivity)getActivity()).refreshMessageFragment();
				}
			}
		});


		return inflate;

	}
}
