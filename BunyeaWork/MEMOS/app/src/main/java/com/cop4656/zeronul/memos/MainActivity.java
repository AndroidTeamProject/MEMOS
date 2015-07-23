package com.cop4656.zeronul.memos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks
{

    private static boolean loggedIn = false;
    private static boolean manager = false;
    private static String userID = "";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        // update the main content by replacing fragments
        Fragment fragment = null;

        switch ( position )
        {
            case 0:
                fragment = HomeFragment.newInstance( position + 1 );
                break;
            case 1:
                fragment = MaintenanceFragment.newInstance( position + 1 );
                break;
            case 2:
                fragment = ReportsFragment.newInstance( position + 1 );
                break;
            case 3:
                fragment = AddItemsFragment.newInstance( position + 1);
                break;
            default:
                break;
        }

        if ( fragment != null )
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    public void onSectionAttached(int number)
    {
        switch (number)
        {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString( R.string.title_section4 );
                break;
        }
    }

    public void restoreActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (!mNavigationDrawerFragment.isDrawerOpen())
        {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //switch to evaluate menu choices
        switch ( id )
        {
            case R.id.action_settings:
                return true;
            case R.id.check_flags:
                showFlagsToast();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity)
        {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void checkIntent()
    {
        Intent intent = getIntent();
        String userID = intent.getStringExtra( "userID" );
        boolean manager = intent.getBooleanExtra( "manager", false );
        boolean loggedIn = intent.getBooleanExtra( "loggedIn", false );
        setManager( manager );
        setLoggedIn( loggedIn );
        setUserID( userID );
    }

    public void setLoggedIn( boolean loggedIn )
    {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public void setManager( boolean manager )
    {
        this.manager = manager;
    }

    public boolean isManager()
    {
        return manager;
    }

    public void setUserID( String userID )
    {
        this.userID = userID;
    }

    public String getUserID()
    {
        return userID;
    }

    public void showFlagsToast()
    {
        CharSequence textMessage = ( "Manager:  " + String.valueOf( manager ) +
                "\n" + "Logged in:  " + String.valueOf( loggedIn ) + "\n"
                + "UserID:       " + userID );
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toastScore = Toast.makeText( context, textMessage, duration );
        toastScore.show();
    }


}