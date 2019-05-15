package com.hsuaxo.tubeup;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsuaxo.tubeup.rxtube.YTContent;
import com.hsuaxo.tubeup.rxtube.YTContentType;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.image_view_thumbnail)
    ImageView mThumbnailView;

    @BindView(R.id.image_view_type)
    ImageView mTypeView;

    @BindView(R.id.text_view_name)
    TextView mNameView;

    @BindView(R.id.text_view_channel)
    TextView mChannelView;

    @BindView(R.id.image_view_more)
    ImageView mMoreView;

    private final RecyclerViewClickListener mListener;

    public SearchViewHolder(final View view, final RecyclerViewClickListener clickListener) {
        super(view);
        mListener = clickListener;
        ButterKnife.bind(this, view);
    }

    public void bindData(final YTContent content) {
        if (!content.thumbnailUrl().equals("")) {
            Picasso.with(itemView.getContext())
                    .load(content.thumbnailUrl())
                    .placeholder(R.drawable.ic_photo_black_24dp)
                    .into(mThumbnailView);
        }
        mNameView.setText(content.name());
        mChannelView.setText(content.channelName());
        mMoreView.setBackgroundColor(Color.TRANSPARENT);
        mMoreView.setOnClickListener(this);
        if (content.type().equals(YTContentType.PLAYLIST)) {
            mTypeView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
}
