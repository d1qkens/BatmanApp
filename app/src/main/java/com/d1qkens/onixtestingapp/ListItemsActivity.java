package com.d1qkens.onixtestingapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.d1qkens.onixtestingapp.Fragments.ListItemsFragment;
import com.d1qkens.onixtestingapp.Utils.NavigationDrawer;


public class ListItemsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        NavigationDrawer.wrapWithNavigationDrawer(this);
        ListItemsFragment listFragment = new ListItemsFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.list_items_fragment, listFragment);
        fragmentTransaction.commit();
    }

}
