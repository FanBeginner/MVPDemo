package com.example.mvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvpdemo.R;
import com.example.mvpdemo.bean.TitleData;



public class NewsRecycleViewAdapter extends RecyclerView.Adapter<NewsRecycleViewAdapter.ViewHolder> {
    private Context mContext;
    private TitleData titleData;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adp_recycler_news,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    public NewsRecycleViewAdapter(Context mContext, TitleData data) {
        this.mContext = mContext;
        this.titleData = data;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(titleData.getResult().getData().get(position).getThumbnail_pic_s())
                .into(holder.imageView);
        holder.tv_title.setText(titleData.getResult().getData().get(position).getTitle());
        holder.tv_author.setText(titleData.getResult().getData().get(position).getAuthor_name());
        holder.tv_category.setText(titleData.getResult().getData().get(position).getCategory());
        holder.tv_date.setText(titleData.getResult().getData().get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return titleData.getResult().getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tv_title;
        private TextView tv_author;
        private TextView tv_date;
        private TextView tv_category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.dap_news_image);
            tv_title=itemView.findViewById(R.id.adp_news_title);
            tv_author=itemView.findViewById(R.id.adp_news_author);
            tv_date=itemView.findViewById(R.id.adp_news_date);
            tv_category=itemView.findViewById(R.id.adp_news_category);
        }
    }
}
