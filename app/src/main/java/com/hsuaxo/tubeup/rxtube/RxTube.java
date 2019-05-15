package com.hsuaxo.tubeup.rxtube;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxTube {

    private final static String YT_API_URL = "https://www.googleapis.com/youtube/v3/";
    private final static int YT_DEFAULT_MAX_RECORDS = 25;

    private final String key;
    private final YTService service;

    public RxTube(final String apiKey) {
        key = apiKey;
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YT_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(YTService.class);
    }

    public Single<YTResult> search(YTContentType type, String searchText, int maxRecords) {
        return service.search(key, type.toString(), searchText, maxRecords);
    }

    public Single<YTResult> search(YTContentType type, String searchText) {
        return search(type, searchText, YT_DEFAULT_MAX_RECORDS);
    }

    public Single<YTResult> searchVideos(String searchText, int maxRecords) {
        return search(YTContentType.VIDEO, searchText, maxRecords);
    }

    public Single<YTResult> searchVideos(String searchText) {
        return searchVideos(searchText, YT_DEFAULT_MAX_RECORDS);
    }

    public Single<YTResult> searchChannels(String searchText, int maxRecords) {
        return search(YTContentType.CHANNEL, searchText, maxRecords);
    }

    public Single<YTResult> searchChannels(String searchText) {
        return searchChannels(searchText, YT_DEFAULT_MAX_RECORDS);
    }

    public Single<YTResult> searchPlaylists(String searchText, int maxRecords) {
        return search(YTContentType.PLAYLIST, searchText, maxRecords);
    }

    public Single<YTResult> searchPlaylists(String searchText) {
        return searchPlaylists(searchText, YT_DEFAULT_MAX_RECORDS);
    }
}
