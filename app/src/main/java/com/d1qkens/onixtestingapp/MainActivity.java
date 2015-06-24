package com.d1qkens.onixtestingapp;

import android.app.Activity;
import android.os.Bundle;
import com.d1qkens.onixtestingapp.Utils.NavigationDrawer;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationDrawer.wrapWithNavigationDrawer(this);
    }
}
