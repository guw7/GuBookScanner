package com.guw.gubook.views.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.guw.gubook.R;
import com.guw.gubook.fragment.FragAbout;
import com.guw.gubook.fragment.FragHome;
import com.guw.gubook.fragment.FragProfile;

public class MenuFragment extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private FragHome fragHome;
    private FragProfile fragProfile;
    private FragAbout fragAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fragment);

        mMainNav = findViewById(R.id.navigation);
        mMainFrame = findViewById(R.id.mainFrame);

        fragHome = new FragHome();
        fragProfile = new FragProfile();
        fragAbout = new FragAbout();

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, new FragHome()).commit();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        setFragment(fragHome);
                        return true;

                    case R.id.navigation_profile:
                        setFragment(fragProfile);
                        return true;

                    case R.id.navigation_about:
                        setFragment(fragAbout);
                        return true;

                    default:
                        return true;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }
}
