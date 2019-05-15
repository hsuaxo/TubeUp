package com.hsuaxo.tubeup.rxtube;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface YTService {

    @GET("search?part=snippet")
    Single<YTResult> search(@Query("key") String key,
                            @Query("type") String type,
                            @Query("q") String searchText,
                            @Query("maxResults") int maxRecords);
}
