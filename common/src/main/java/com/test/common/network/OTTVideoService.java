package com.test.common.network;

import com.test.common.network.models.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OTTVideoService {
    @GET("search")
    Call<SearchResult> search(@Query("query") String query,
                              @Query("page") int page,
                              @Query("per_page") int offset
    );
}
