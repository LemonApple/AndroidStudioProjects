package com.example.finalassignment.adapter;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.finalassignment.calendar.Constant;
import com.example.finalassignment.fragment.ScheduleFragment;



public class SchedulePagerAdapter extends FragmentStatePagerAdapter {
    // 碎片页适配器的构造函数
    public SchedulePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // 获取碎片Fragment的个数，一年有52个星期
    public int getCount() {
        return 52;
    }

    // 获取指定星期的碎片Fragment
    public Fragment getItem(int position) {
        return ScheduleFragment.newInstance(position + 1);
    }

    // 获得指定星期的标题文本
    public CharSequence getPageTitle(int position) {
        return new String("第" + Constant.xuhaoArray[position + 1] + "周");
    }
}
