package com.hsuaxo.tubeup;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.hsuaxo.tubeup.rxtube.YTContent;

@Entity(tableName = "favorites", indices = {@Index("name")})
public class FavoriteContent {
    @NonNull
    @PrimaryKey
    public String id;
    public String type;
    public String name;
    public String description;
    public String date;
    @ColumnInfo(name = "channel_name")
    public String channelName;
    @ColumnInfo(name = "thumbnail_url")
    public String thumbnailUrl;
    public String url;

    public FavoriteContent() {
    }

    public FavoriteContent(YTContent content) {
        id = content.id();
        type = content.type().toString();
        name = content.name();
        description = content.description();
        date = content.date();
        channelName = content.channelName();
        thumbnailUrl = content.thumbnailUrl();
        url = content.url();
    }
}
