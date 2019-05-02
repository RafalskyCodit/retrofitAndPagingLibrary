package com.example.pagingretrofit.model;

public class SearchFilter {
    private String query;

    public SearchFilter(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
