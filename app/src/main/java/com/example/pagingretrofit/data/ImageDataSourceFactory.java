package com.example.pagingretrofit.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.example.pagingretrofit.api.ImageResourceApi;
import com.example.pagingretrofit.model.Hit;
import com.example.pagingretrofit.model.SearchFilter;

public class ImageDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, Hit>> liveDataSource;
    private SearchFilter filter;

    public ImageDataSourceFactory(){
        liveDataSource = new MutableLiveData<>();
        filter = new SearchFilter(ImageResourceApi.DEFAULT_QUERY);
    }

    @Override
    public DataSource create() {
        ImageDataSource dataSource = new ImageDataSource(filter);
        liveDataSource.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Hit>> getLiveDataSource() {
        return liveDataSource;
    }
}
