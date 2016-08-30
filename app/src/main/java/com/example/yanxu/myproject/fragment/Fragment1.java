package com.example.yanxu.myproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanxu.myproject.R;

public class Fragment1 extends Fragment {

	/**
	 * 是否将root附加到布局文件的根视图上
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_1, container, false);
		return view;
	}

}
