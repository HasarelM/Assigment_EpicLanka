package com.example.assigment_epic.view_model;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assigment_epic.endpoints.StatusCallback;
import com.example.assigment_epic.model.BreakingBadModel;
import com.example.assigment_epic.retrofit.EndpointCalls;
import com.example.assigment_epic.utills.Utils;

import java.util.ArrayList;

public class BrakingBadViewModel  extends ViewModel {

    private static final String TAG = BrakingBadViewModel.class.getSimpleName();
    private EndpointCalls mRepository;
    private MutableLiveData<ArrayList<BreakingBadModel>> mBreakingBadDataArrayList = new MutableLiveData<>();

    public BrakingBadViewModel(Context mContext, EndpointCalls repo) {
        mRepository = repo;
    }

    public MutableLiveData<ArrayList<BreakingBadModel>> getBreakingBadDataList(){
        mRepository.getBrakingBadDataList(new StatusCallback() {
            @Override
            public void onSuccess(ArrayList result) {
                if (Utils.isListNotNullAndEmpty(result)){
                    mBreakingBadDataArrayList.postValue(result);
                }
            }

            @Override
            public void onError(String error) {
            }
        });

        return mBreakingBadDataArrayList;
    }

}
