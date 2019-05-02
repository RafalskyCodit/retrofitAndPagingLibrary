package com.example.pagingretrofit.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.example.pagingretrofit.api.ImageResourceApi;
import com.example.pagingretrofit.data.ImageDataSourceFactory;
import com.example.pagingretrofit.model.Hit;
import com.example.pagingretrofit.model.SearchFilter;

public class MainViewModel extends ViewModel {
    private LiveData<PagedList<Hit>> livePagedList;
    private MutableLiveData<PageKeyedDataSource<Integer, Hit>> liveDataSource;

    public MainViewModel(){
        ImageDataSourceFactory factory = new ImageDataSourceFactory();
        liveDataSource = factory.getLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(ImageResourceApi.PER_PAGE)
                .build();

        livePagedList = new LivePagedListBuilder<>(factory, config)
                .build();
    }

    public LiveData<PagedList<Hit>> getLivePagedList() {
        return livePagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Hit>> getLiveDataSource() {
        return liveDataSource;
    }

    public void updateSearchFilter(SearchFilter filter){
    }

}
