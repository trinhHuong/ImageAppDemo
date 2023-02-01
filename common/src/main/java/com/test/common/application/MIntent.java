package com.test.common.application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.Serializable;
public class MIntent extends Intent {
    public MIntent() {
    }

    public MIntent(Intent o) {
        super(o);
    }

    public MIntent(String action) {
        super(action);
    }

    public MIntent(String action, Uri uri) {
        super(action, uri);
    }

    public MIntent(Context packageContext, Class<?> cls) {
        super(packageContext, cls);
    }

    public MIntent(String action, Uri uri, Context packageContext, Class<?> cls) {
        super(action, uri, packageContext, cls);
    }
    public Intent getIntent() {
        return this;
    }

    public void putObject(MKeys key, Serializable objects) {
        this.putExtra(key.toString(), objects);
    }

    public <T> T getObject(MKeys keys) {
        return (T) this.getSerializableExtra(keys.toString());
    }

    public void putString(MKeys key, String value) {
        this.putExtra(key.toString(), value);
    }

    public void putInt(MKeys key, int value) {
        this.putExtra(key.toString(), value);
    }

    public void putDouble(MKeys key, double value) {
        this.putExtra(key.toString(), value);
    }
    public void putLong(MKeys key, long value) {
        this.putExtra(key.toString(), value);
    }

    public void putBoolean(MKeys key, boolean value) {
        this.putExtra(key.toString(), value);
    }
    public int getInt(MKeys key) {
        return this.getIntExtra(key.toString(), Integer.MIN_VALUE);
    }

    public double getDouble(MKeys key) {
        return this.getDoubleExtra(key.toString(), Integer.MIN_VALUE);
    }

    public double getDouble(MKeys key, double defValue) {
        return this.getDoubleExtra(key.toString(), defValue);
    }

    public int getInt(MKeys key, int defaultValue) {
        return this.getIntExtra(key.toString(), defaultValue);
    }

    public void put(MKeys key, int value) {
        this.putExtra(key.toString(), value);
    }

    public void put(MKeys key, long value) {
        this.putExtra(key.toString(), value);
    }

    public void put(MKeys key, String value) {
        this.putExtra(key.toString(), value);
    }

    public void put(MKeys key, Serializable value) {
        this.putExtra(key.toString(), value);
    }

    public long getLong(MKeys keys) {
        return this.getLongExtra(keys.toString(), Long.MIN_VALUE);
    }

    public String getString(MKeys key) {
        return this.getStringExtra(key.toString());
    }

    public boolean getBoolean(MKeys key) {
        return this.getBooleanExtra(key.toString(), false);
    }

    public boolean getBoolean(MKeys key, boolean defaultValue) {
        return this.getBooleanExtra(key.toString(), defaultValue);
    }

}
