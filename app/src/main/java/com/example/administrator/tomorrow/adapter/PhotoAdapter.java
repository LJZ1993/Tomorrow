package com.example.administrator.tomorrow.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.tomorrow.R;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private FragmentActivity mActivity;

    public PhotoAdapter(FragmentActivity mActivity) {
        this.mActivity = mActivity;
        Log.w("onBindViewHolder", "onBindViewHolder1111111111111111111");
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_photo, parent, false);
        PhotoViewHolder holder = new PhotoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Picasso.with(mActivity).load("http://ww3.sinaimg.cn/large/610dc034jw1f88ylsqjvqj20u011hn5i.jpg").placeholder(R.drawable.ic_add).error(R.drawable.ic_launcher).into(holder.mItemPhotoIv);
        holder.mItemPhotoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  HttpDataUtils.getPhotoData(0, 1, 20);
                Log.w("onResponse", "onClick    ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView mItemPhotoIv;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            mItemPhotoIv = (ImageView) itemView.findViewById(R.id.m_item_photo_iv);
        }
    }
}
