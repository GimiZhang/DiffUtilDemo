package com.example.administrator.diffutildemo;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */

public class DiffCallBack extends DiffUtil.Callback {

    List<String> oldData,newData;

    public DiffCallBack(List<String> oldData, List<String> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if(!oldData.get(oldItemPosition).equals(newData.get(newItemPosition))){
            return false;
        }
        return true;
    }
}
