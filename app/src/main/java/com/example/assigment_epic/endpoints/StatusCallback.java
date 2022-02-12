package com.example.assigment_epic.endpoints;

import java.util.ArrayList;

public interface StatusCallback<T> {
    void onSuccess(ArrayList<T> result);

    void onError(String error);
}
