package com.test.common.network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public abstract class BaseCallback<T> implements Callback<T> {
    private static final String TAG = "BaseCallback";
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.e(TAG, "Request: " + call.request().toString());
        if (response.isSuccessful()) {
            onResponse(response.body());
        } else {
            onError("Service is busy. Please try later.");
        }

    }
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(TAG, "Request: " + call.request().toString() + " " + t.toString());
        onError("Service is busy. Please try later.");
    }


    public abstract void onError(String errorMessage);

    public abstract void onResponse(T data);
}
