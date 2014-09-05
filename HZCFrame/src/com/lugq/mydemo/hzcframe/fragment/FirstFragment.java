package com.lugq.mydemo.hzcframe.fragment;

import com.lugq.mydemo.hzcframe.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * First
 * @ClassName: FirstFragment
 * @author lugq
 * @date 2014年9月4日 下午4:13:16
 *
 */
public class FirstFragment extends Fragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.first_fragment, container, false);
	}
}
