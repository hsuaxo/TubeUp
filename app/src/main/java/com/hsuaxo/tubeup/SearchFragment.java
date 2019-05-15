package com.hsuaxo.tubeup;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hsuaxo.tubeup.rxtube.RxTube;
import com.hsuaxo.tubeup.rxtube.YTContent;
import com.hsuaxo.tubeup.rxtube.YTContentType;
import com.hsuaxo.tubeup.rxtube.YTResult;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SearchFragment extends DaggerFragment {

    @Inject
    RxTube mRxTube;

    @Inject
    AppDatabase mDatabase;

    @BindView(R.id.edit_text_search)
    EditText mEditText;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.recycler_view_search)
    RecyclerView mRecyclerView;

    private SearchAdapter mAdapter;
    private Unbinder unbinder;

    private final RecyclerViewClickListener clickListener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            final YTContent content = mAdapter.getItem(position);
            final DialogInterface.OnClickListener callback = new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialog, final int id) {
                    switch (id) {
                        case 0:
                            break;
                        case 1:
                            final Uri uri = Uri.parse(content.url());
                            final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case 2:
                            final FavoriteContent favContent = new FavoriteContent(content);
                            mDatabase.favoriteDao().insertFavorites(favContent);
                            break;
                    }
                }
            };
            OptionsDialog.show(getContext(), content.name().toUpperCase(), R.array.youtube_options, callback);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setSearch();
        return view;
    }

    private void setSearch() {
        RxTextView.textChangeEvents(mEditText)
                .doOnNext(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(TextViewTextChangeEvent event) throws Exception {
                        setProgressBarVisible(true);
                    }
                })
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(new Function<TextViewTextChangeEvent, String>() {
                    @Override
                    public String apply(TextViewTextChangeEvent text) {
                        return text.text().toString();
                    }
                })
                .distinctUntilChanged()
                .flatMapSingle(new Function<String, Single<YTResult>>() {
                    @Override
                    public Single<YTResult> apply(String s) {
                        return mRxTube.search(YTContentType.ANY, s).onErrorReturn(new Function<Throwable, YTResult>() {
                            @Override
                            public YTResult apply(Throwable t) throws Exception {
                                t.printStackTrace();
                                return YTResult.empty();
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YTResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(YTResult result) {
                        mProgressBar.setVisibility(View.GONE);
                        if (mAdapter == null) {
                            mAdapter = new SearchAdapter(result.items, clickListener);
                        } else {
                            mAdapter.replaceData(result.items);
                        }
                        mRecyclerView.setAdapter(mAdapter);
                        onComplete();
                    }

                    @Override
                    public void onComplete() {
                        setProgressBarVisible(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    private void setProgressBarVisible(final boolean visible) {
        if (visible) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
