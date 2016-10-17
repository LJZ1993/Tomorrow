package com.example.administrator.tomorrow.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.bean.ArticleListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {


    private FragmentActivity mActivity;
    List<ArticleListBean.ResultBean> mResultBeanList;


    public ArticleAdapter(FragmentActivity mActivity, List<ArticleListBean.ResultBean> articleListBeanList) {
        mActivity = mActivity;
        mResultBeanList = articleListBeanList;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.mArticleTvTime.setText(mResultBeanList.get(position).createdAt);
        holder.mArticleTvAuthor.setText(mResultBeanList.get(position)._id);
        holder.mArticleLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("ArticleAdapter", "点击了ArticleAdapter的item");
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.m_article_ll)
        LinearLayout mArticleLl;
        @Bind(R.id.m_article_tv_content)
        TextView mArticleTvContent;
        @Bind(R.id.m_article_tv_author)
        TextView mArticleTvAuthor;
        @Bind(R.id.m_article_tv_time)
        TextView mArticleTvTime;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
