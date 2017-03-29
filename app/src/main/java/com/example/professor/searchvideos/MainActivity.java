package com.example.professor.searchvideos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.professor.searchvideos.database.ReadWriteFilmsInStorage;
import com.example.professor.searchvideos.dialogs.ExitDialog;
import com.example.professor.searchvideos.fragments.FragmentsGenerator;

import com.example.professor.searchvideos.models.DescriptionFilm;
import com.example.professor.searchvideos.util.Constants;
import com.example.professor.searchvideos.util.Shared;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
//import com.example.professor.searchvideos.util.SaveImages;

public class MainActivity extends AppCompatActivity {
     @BindView(R.id.toolbar) Toolbar toolbar;
     @BindView(R.id.drawer_layout) DrawerLayout layout;
    private ActionBarDrawerToggle myToggle;
    private NavigationView navigation;
    private Unbinder unbind;
    public static final String TAG = MainActivity.class.getSimpleName();
    private android.support.v4.app.FragmentTransaction trans;
    public boolean tag = false;
    private FragmentManager fm;
    public String fragmentName;
    private Fragment fragment;
    private View.OnClickListener listener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragment(FragmentsGenerator.showSavedFilms());
        fm = getSupportFragmentManager();
        unbind = ButterKnife.bind(this);
        Shared.setActivity(this);
       /* toolbar = (Toolbar) findViewById();
        layout = (DrawerLayout) findViewById();*/
        listener = new DrawerButtons();
        myToggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.open_drawer
                , R.string.close_drawer) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                setNavigationButtons();
            }
        };
        layout.setDrawerListener(myToggle);
        navigation = (NavigationView) findViewById(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return false;
            }
        });


        Log.d(TAG, "onStart: 1000");
        if (savedInstanceState == null) {

        }
    }

    public void createFragment(Fragment fr) {
        Log.d(TAG, "onStart: 4000");
        trans = getSupportFragmentManager().beginTransaction();
        this.fragment = fr;
        trans.replace(R.id.frame, fr, Constants.REPLACE_FRAGMENT);
        trans.addToBackStack(fr.getClass().getSimpleName());
        Log.d(TAG, "createFragment: "+fr.getClass().getSimpleName());
        trans.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setToolbar(null, null);
        Log.d(TAG, "onStart: 2000");

    }

    @Override
    public void onBackPressed() {
        //
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
            Log.d(TAG, "onBackPressed: " +fm.findFragmentById(R.id.frame));

        }
        if (fm.getBackStackEntryCount() == 1) {
            ExitDialog exit = new ExitDialog();
            exit.show(getFragmentManager(), Constants.EXIT);
        }
        Log.d(TAG, "onBackPressed: " + fm.getBackStackEntryCount());
        Log.d(TAG, "onBackPressed: " +fm.findFragmentById(R.id.frame));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            createFragment(fm.getFragment(savedInstanceState, Constants.LANDSCAPE_KEY));
            Log.d(TAG, "onRestoreInstanceState: " + savedInstanceState);
        }
        Log.d(TAG, "onStart: 3000");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.fragment = fm.findFragmentById(R.id.frame);
        Log.d(TAG, "onSaveInstanceState: "+fm.findFragmentById(R.id.frame));
        fm.putFragment(outState, Constants.LANDSCAPE_KEY, fragment);
        Log.d(TAG, "onSaveInstanceState: " + 445);
    }

    public void setToolbar(final DescriptionFilm film, final ImageView image) {
        if (tag == false) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setNavigationIcon(R.mipmap.ic_menu_white_18dp);
            Log.d(TAG, "setToolbar: " + toolbar.getMenu().size());
            if (toolbar.getMenu().size() > 0) {
                toolbar.getMenu().clear();
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layout.openDrawer(GravityCompat.START);
                    Log.d(TAG, "onClick: home is select");
                }
            });
        }
        if (tag) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
            if (toolbar.getMenu().size() == 0) {
                toolbar.inflateMenu(R.menu.menu);
            }
            Log.d(TAG, "setToolbar: " + toolbar.getMenu().size());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                    tag = false;
                    setToolbar(null,null);
                }
            });
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(film != null){
                        ReadWriteFilmsInStorage.writeFilm(film);
                      //  SaveImages.saveImage(image,film.getTitle(),getBaseContext());

                    }
                    return false;
                }
            });
        }
    }

    private void setNavigationButtons() {
        findViewById(R.id.saved_movies_button).setOnClickListener(listener);
        findViewById(R.id.search_with_title_button).setOnClickListener(listener);
        findViewById(R.id.search_with_director_button).setOnClickListener(listener);
    }

    private final class DrawerButtons implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.saved_movies_button:
                    createFragment(FragmentsGenerator.showSavedFilms());
                    layout.closeDrawers();
                    Shared.setPosition(0);
                    break;

                case R.id.search_with_title_button:
                    createFragment(FragmentsGenerator.searcFilms(getResources()
                            .getString(R.string.search_with_title), 1));
                    layout.closeDrawers();
                    Shared.setPosition(0);
                    break;

                case R.id.search_with_director_button:
                    createFragment(FragmentsGenerator.searcFilms(getResources()
                            .getString(R.string.search_with_director), 2));
                    layout.closeDrawers();
                    Shared.setPosition(0);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        unbind.unbind();
        Shared.setPosition(0);
        super.onDestroy();
    }
}
