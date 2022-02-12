package com.example.assigment_epic.retrofit;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.assigment_epic.endpoints.EndPoint;
import com.example.assigment_epic.endpoints.StatusCallback;
import com.example.assigment_epic.model.BreakingBadModel;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndpointCalls {
    private static final String TAG = EndpointCalls.class.getSimpleName();

    private ArrayList<BreakingBadModel> mBrakingBadDataList;
    private Context mContext;


    public EndpointCalls(Context context) {
        mBrakingBadDataList = new ArrayList<>();
        mContext = context;
    }

    public void getBrakingBadDataList(StatusCallback callback){
        EndPoint endPoint = RetrofitClient.getLoginClient().create(EndPoint.class);
        Call<ArrayList<BreakingBadModel>> call = endPoint.getAllBrakingBadData(BaseURL.URL+"characters?limit=5");
        call.enqueue(new Callback<ArrayList<BreakingBadModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BreakingBadModel>> call, Response<ArrayList<BreakingBadModel>> response) {
                if (response.code()==200){
                    if (response.body()!=null && !response.body().isEmpty()){
                        mBrakingBadDataList = response.body();
                        callback.onSuccess(mBrakingBadDataList);
                    }else {
                        callback.onError("There is no Data!");
                    }
                }else {
                    callback.onError("Somethings went wrong!");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BreakingBadModel>> call, Throwable t) {
                callback.onError("Request is failed!");
            }
        });

    }
}
