package com.test.common.application;

import androidx.annotation.NonNull;
public enum MKeys {
    LIST_PHOTOS("LIST_PAGE_PHOTOS"),
    ITEM_POSITION("ITEM_POSITION");
    private final String name;

    MKeys(String name) {
        this.name = name;
    }
    @NonNull

    @Override
    public String toString() {
        return this.name;
    }
}
