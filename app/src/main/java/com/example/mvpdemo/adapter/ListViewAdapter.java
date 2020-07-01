package com.example.mvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvpdemo.R;
import com.example.mvpdemo.bean.Emoji;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ListViewAdapter extends BaseAdapter {
    List<Emoji> emojiList=new ArrayList<>();
    Context mContext;

    public ListViewAdapter(Context context,List<Emoji> emojiList) {
        this.emojiList = emojiList;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return emojiList.size();
    }

    @Override
    public Object getItem(int position) {
        return emojiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.adp_list,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.im_emoji=convertView.findViewById(R.id.im_item_emoji);
            viewHolder.tv_state=convertView.findViewById(R.id.tv_item_state);
            viewHolder.tv_star=convertView.findViewById(R.id.tv_item_star);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.im_emoji.setImageResource(emojiList.get(position).getImageId());
        viewHolder.tv_state.setText(emojiList.get(position).getState());
        viewHolder.tv_star.setText(emojiList.get(position).getStar());
        return convertView;
    }
    class ViewHolder{
        ImageView im_emoji;
        TextView tv_state;
        TextView tv_star;
    }

}
