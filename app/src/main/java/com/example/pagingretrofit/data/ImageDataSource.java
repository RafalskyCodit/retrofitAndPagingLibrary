package com.example.pagingretrofit.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.pagingretrofit.api.ImageResourceApi;
import com.example.pagingretrofit.model.Hit;
import com.example.pagingretrofit.model.ImageResponse;
import com.example.pagingretrofit.model.RetrofitClient;
import com.example.pagingretrofit.model.SearchFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDataSource extends PageKeyedDataSource<Integer, Hit> {
    private ImageResourceApi api;
    private SearchFilter filter;

    public ImageDataSource(SearchFilter filter){
        api = RetrofitClient.getRetrofitClient().getApi();
        this.filter = filter;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Hit> callback) {
        final int initialPage = 1;
        api.getImages(filter.getQuery(), initialPage, api.PER_PAGE).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(@NonNull Call<ImageResponse> call, @NonNull retrofit2.Response<ImageResponse> response) {
                if (response.isSuccessful()){
                    ImageResponse imageResponse = response.body();
                    if (imageResponse != null){
                        List<Hit> hits = imageResponse.getHits();
                        callback.onResult(hits, 0, (int)imageResponse.getTotalHits(), null, initialPage + 1);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ImageResponse> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Hit> callback) {
        api.getImages(filter.getQuery(), params.key, api.PER_PAGE).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(@NonNull Call<ImageResponse> call, @NonNull Response<ImageResponse> response) {
                if (response.isSuccessful()){
                    ImageResponse imageResponse = response.body();
                    if (imageResponse != null){
                        Integer key = params.key > 1 ? params.key - 1 : null;
                        callback.onResult(imageResponse.getHits(), key);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ImageResponse> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Hit> callback) {
        api.getImages(filter.getQuery(), params.key, api.PER_PAGE).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(@NonNull Call<ImageResponse> call, @NonNull Response<ImageResponse> response) {
                if (response.isSuccessful()){
                    ImageResponse imageResponse = response.body();
                    if (imageResponse != null){
                        callback.onResult(imageResponse.getHits(), params.key + 1);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ImageResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
