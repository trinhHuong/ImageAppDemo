package com.test.common.ultilities;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.common.application.MKeys;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MSharedSession {
    private static MSharedSession instance;
    private Bundle bundle = new Bundle();

    public MSharedSession() {
        bundle = new Bundle();
    }

    public static MSharedSession getInstance() {
        if (instance == null) {
            instance = new MSharedSession();
        }
        return instance;
    }

    public MSharedSession putInt(MKeys key, int value) {
        bundle.putInt(key.toString(), value);
        return this;
    }

    public MSharedSession putString(MKeys key, String value) {
        bundle.putString(key.toString(), value);
        return this;
    }

    public MSharedSession putLong(MKeys key, long value) {
        bundle.putLong(key.toString(), value);
        return this;
    }

    public MSharedSession putBoolen(MKeys key, Boolean value) {
        bundle.putBoolean(key.toString(), value);
        return this;
    }

    public MSharedSession putFloat(MKeys key, Float value) {
        bundle.putFloat(key.toString(), value);
        return this;
    }

    public MSharedSession putObject(MKeys key, Serializable value) {
        bundle.putSerializable(key.toString(), value);
        return this;
    }

    public MSharedSession put(MKeys key, List<String> value) {
        Gson gson = new Gson();
        String json = gson.toJson(value);
        putString(key, json);
        return this;
    }

    public MSharedSession put(MKeys key, int value) {
        return putInt(key, value);
    }

    public MSharedSession put(MKeys key, String value) {
        return putString(key, value);
    }

    public MSharedSession put(MKeys key, long value) {
        return putLong(key, value);
    }

    public MSharedSession put(MKeys key, Boolean value) {
        return putBoolen(key, value);
    }

    public MSharedSession put(MKeys key, Float value) {
        return putFloat(key, value);
    }

    public MSharedSession put(MKeys key, Serializable value) {
        return putObject(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getObject(MKeys key) {
        return (T) bundle.getSerializable(key.toString());
    }

    public String getString(MKeys key) {
        return bundle.getString(key.toString(), null);
    }

    public int getInt(MKeys key) {
        return bundle.getInt(key.toString(), 0);
    }

    public int getInt(MKeys key, int defaultValue) {
        return bundle.getInt(key.toString(), defaultValue);
    }

    public long getLong(MKeys key) {
        return bundle.getLong(key.toString(), 0L);
    }

    public long getLong(MKeys key, long defaultValue) {
        return bundle.getLong(key.toString(), defaultValue);
    }

    public boolean getBoolen(MKeys key) {
        return bundle.getBoolean(key.toString(), false);
    }

    public MSharedSession remove(MKeys key) {
        bundle.remove(key.toString());
        return this;
    }

    public MSharedSession removeAll() {
        bundle.clear();
        return this;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public Boolean isExist(MKeys key) {
        return bundle.containsKey(key.toString());
    }


    public List<String> get(MKeys key) {
        String stringValue = getString(key);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(stringValue, type);
    }

}
