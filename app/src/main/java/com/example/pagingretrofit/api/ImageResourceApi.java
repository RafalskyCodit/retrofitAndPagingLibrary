package com.example.pagingretrofit.api;

import com.example.pagingretrofit.model.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageResourceApi {
    int PER_PAGE = 10;
    String DEFAULT_QUERY = "flowers";

    @GET("?key=12277686-7e23a4b73c1e6604427408604")
    Call<ImageResponse> getImages(@Query("q") String query,
                                  @Query("page") int page,
                                  @Query("per_page") int perPage);
}
