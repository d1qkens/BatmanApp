package com.d1qkens.onixtestingapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class ListItemsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        ListItemsFragment listFragment = new ListItemsFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.list_items_fragment, listFragment);
        fragmentTransaction.commit();

        new DrawerBuilder().withActivity(this)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Batman").withIcon(R.drawable.batmanlogovsjoker),
                        new PrimaryDrawerItem().withName("Joker").withIcon(R.drawable.jokersign),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Settings").withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName("Logout").withIcon(FontAwesome.Icon.faw_sign_out))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        if (i == 4) {
                            SaveSharedPreferences.clearUserName(ListItemsActivity.this);
                            startActivity(new Intent(ListItemsActivity.this, MainActivity.class));
                        }
                        return false;
                    }
                })
                .build();
    }

}
