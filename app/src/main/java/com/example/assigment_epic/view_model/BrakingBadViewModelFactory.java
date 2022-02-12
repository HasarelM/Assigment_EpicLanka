package com.example.assigment_epic.view_model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.assigment_epic.retrofit.EndpointCalls;

public class BrakingBadViewModelFactory implements ViewModelProvider.Factory{
    private final Context mContext;
    private final EndpointCalls repo;
    public BrakingBadViewModelFactory(Context context, EndpointCalls endpointCalls) {
        this.mContext = context;
        this.repo = endpointCalls;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BrakingBadViewModel(mContext,repo);
    }
}
