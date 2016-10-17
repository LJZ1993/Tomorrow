package com.example.administrator.tomorrow.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.bean.MovieListBean;
import com.example.administrator.tomorrow.listener.LoadingMoreBottomListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class MovieAdapter extends RecyclerView.Adapter {
    private static final int ITEM_PROGR = 0;
    private static final int ITEM_VIEW = 1;
    //底部加载更多监听器
    LoadingMoreBottomListener loadingMoreBottomListener;
    private RecyclerView mRecycler;
    private FragmentActivity mActivity;
    private List<MovieListBean.SubjectsBean> mSubjectsBean;
    private boolean isLoading;


    public MovieAdapter(FragmentActivity mActivity, RecyclerView mRecycler, List<MovieListBean.SubjectsBean> mSubjectsBean) {
        this.mActivity = mActivity;
        this.mSubjectsBean = mSubjectsBean;
        this.mRecycler = mRecycler;
        final RecyclerView.LayoutManager manager = mRecycler.getLayoutManager();
        if (manager instanceof LinearLayoutManager) {

            //给mRecycler设置滑动监听器
            mRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //获取已可见的item的最大postion
                    int visibleItemPosition = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                    //获取已经加载的item的总数
                    int itemCount = ((LinearLayoutManager) manager).getItemCount();
                    if (!isLoading && itemCount <= visibleItemPosition + 5) {
                        loadingMoreBottomListener.onLoadingMore();
                        isLoading = true;
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_VIEW) {
            View view = inflater.inflate(R.layout.item_movie, parent, false);
            MovieViewHolder holder = new MovieViewHolder(view);
            return holder;
        } else {
            View view = inflater.inflate(R.layout.footerview, parent, false);
            ProgressViewHolder holder = new ProgressViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            MovieListBean.SubjectsBean subjectsBean = mSubjectsBean.get(position);
            //电影名称和原名
            ((MovieViewHolder) holder).mMoviewTvTitle.setText((position + 1) + "、" + subjectsBean.getTitle() + "/" + subjectsBean.getOriginal_title());
            //导演名
            String dir = "";
            for (MovieListBean.SubjectsBean.DirectorsBean director : subjectsBean.getDirectors()) {
                dir = dir + " " + director.getName();
            }
            ((MovieViewHolder) holder).mMoviewTvDesc.setText("导演：" + dir);
            //主演
            String casts = "";
            for (MovieListBean.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
                casts = castsBean.getName() + casts;
            }
            ((MovieViewHolder) holder).mMoviewTvArt.setText("主演：" + casts);
            //年份和类型
            ((MovieViewHolder) holder).mMoviewTvType.setText(subjectsBean.getYear() + "/" + subjectsBean.getGenres());
            //电影分数
            ((MovieViewHolder) holder).mMoviewTvGrade.setText(subjectsBean.getRating().getAverage() + "");
            Glide.with(mActivity).load(subjectsBean.getImages().getSmall()).error(R.drawable.ic_add).placeholder(R.drawable.ic_menu_camera).into(((MovieViewHolder) holder).mItemMoviewIv);
        } else {
            ((ProgressViewHolder) holder).mFooterviewPb.setIndeterminate(false);
        }
    }

    @Override
    public int getItemCount() {
        return mSubjectsBean.size() > 0 ? mSubjectsBean.size() : 0;
    }

    /**
     * 获取position位置的item的type
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mSubjectsBean.get(position) == null ? ITEM_PROGR : ITEM_VIEW;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.m_item_moview_iv)
        ImageView mItemMoviewIv;
        @Bind(R.id.m_moview_tv_title)
        TextView mMoviewTvTitle;
        @Bind(R.id.m_moview_tv_desc)
        TextView mMoviewTvDesc;
        @Bind(R.id.m_moview_tv_art)
        TextView mMoviewTvArt;
        @Bind(R.id.m_moview_tv_type)
        TextView mMoviewTvType;
        @Bind(R.id.m_moview_tv_grade)
        TextView mMoviewTvGrade;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.m_footerview_pb)
        ProgressBar mFooterviewPb;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setLoadingMoreBottomListener(LoadingMoreBottomListener loadingMoreBottomListener) {
        this.loadingMoreBottomListener = loadingMoreBottomListener;
    }

    public void unLoading() {
        isLoading = false;
    }
}
