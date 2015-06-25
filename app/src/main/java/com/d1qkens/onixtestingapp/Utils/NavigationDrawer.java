package com.d1qkens.onixtestingapp.Utils;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.d1qkens.onixtestingapp.MainActivity;
import com.d1qkens.onixtestingapp.R;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class NavigationDrawer {
    private static final int BATMAN = 1;
    private static final int JOKER = 2;
    private static final int SETTINGS = 3;
    private static final int LOGOUT = 4;

    public static void wrapWithNavigationDrawer(final Activity activity) {
        new DrawerBuilder().withActivity(activity)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.batman).withIcon(R.drawable.batmanmenusketch),
                        new PrimaryDrawerItem().withName(R.string.joker).withIcon(R.drawable.jokermenubigger),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_logout).withIcon(FontAwesome.Icon.faw_sign_out))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        switch (i) {
                            case (BATMAN):
                                break;
                            case (JOKER):
                                break;
                            case (SETTINGS):
                                break;
                            case (LOGOUT):
                                SaveSharedPreferences.clearUserName(activity);
                                activity.startActivity(new Intent(activity, MainActivity.class));
                                break;
                        }
                        return false;
                    }
                })
                .build();
    }
}
