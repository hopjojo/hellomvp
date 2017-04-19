package com.youche.ycsaas.module.movie;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.youche.ycsaas.R;
import com.youche.ycsaas.app.BaseActivity;
import com.youche.ycsaas.util.UIUtils;

import es.dmoral.toasty.Toasty;

public class MovieListActivity extends BaseActivity implements MovieContract.View {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movielist_act);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_movielist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MoviePresenter moviePresenter = new MoviePresenter(this);
        moviePresenter.getTopMovie(0, 10);
    }

    @Override
    public void showData(MovieEntity movieEntity) {
        Toasty.info(this, movieEntity.getTitle()).show();
        mRecyclerView.setAdapter(new MovieAdapter(this, movieEntity.getSubjects()));
    }


    @Override
    public void startLoading() {
        UIUtils.showLoading(this, getString(R.string.message_loading));
    }

    @Override
    public void complete() {
        UIUtils.dismissLoading(this);
    }

    @Override
    public void error(Throwable e) {
        UIUtils.dismissLoading(this);
        Toasty.error(this, e.getMessage()).show();
    }
}
