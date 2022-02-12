package com.example.assigment_epic.endpoints;

import com.example.assigment_epic.model.BreakingBadModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface EndPoint {

    @Headers({"Content-Type: application/json"})
    @GET
    Call<ArrayList<BreakingBadModel>> getAllBrakingBadData(@Url String Url);
}
