package com.example.administrator.diffutildemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mDatas;
    public ItemClickListener clickListener;
    public int position;

    public MyAdapter(Context mContext, List<String> datas) {
        this.mContext = mContext;
        mDatas = datas;
    }

    public void setDatas(List<String> datas){
        mDatas = datas;
    }

    public interface ItemClickListener {
        void onClick(String str);
    }

    public void setItemClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rcycle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        this.position = position;
        return super.getItemId(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ItemViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.id_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(mDatas.get(position));
                }
            });
        }
    }
}
