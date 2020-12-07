package com.example.a10_combination_control.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.a10_combination_control.R;

public class BookReviewFragment extends Fragment {
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_book_review.xml生成视图对象
        mView = inflater.inflate(R.layout.fragment_book_review, container, false);
        return mView;
    }
}
