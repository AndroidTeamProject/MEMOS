package com.cop4656.zeronul.memos;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Zero Nul on 7/16/2015.
 */
public class HomeFragment extends Fragment
{
    private static boolean loggedIn = false;
    private static boolean manager = false;
    private static String userID = "";
    private static String password = "";

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber)
    {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        userID = getArguments().getString( "userID", "");
        manager = getArguments().getBoolean( "manager", false);
        loggedIn = getArguments().getBoolean( "loggedIn", false);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public void setUserID( String userID )
    {
        this.userID = userID;
        Intent intent = new Intent( getActivity().getBaseContext(), MainActivity.class );
        intent.putExtra( "userID", userID );
        getActivity().startActivity( intent );

    }

    public String getUserID()
    {
        return userID;
    }

    public void getPassword()
    {
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public void setLoggedIn( boolean loggedIn )
    {
        this.loggedIn = loggedIn;
        Intent intent = new Intent( getActivity().getBaseContext(), MainActivity.class );
        intent.putExtra( "loggedIn", loggedIn );
        getActivity().startActivity( intent );
    }

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public void setManager( boolean manager )
    {
        this.manager = manager;
        Intent intent = new Intent( getActivity().getBaseContext(), MainActivity.class );
        intent.putExtra( "manager", manager );
        getActivity().startActivity( intent );
    }

    public boolean isManager()
    {
        return manager;
    }
}
