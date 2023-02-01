package com.test.imageapp.navigation;

import androidx.navigation.NavController;

/**
 * Created by huongtrinh on 1/18/23.
 */
public class Navigator {
    NavController navController = null;
    public void bind(NavController navController) {
        this.navController = navController;
    }
    public void unBind() {
        navController = null;
    }
}
