package com.cop4656.zeronul.memos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Zero Nul on 7/22/2015.
 */
public class MaintenanceFragment extends Fragment
{
    private static boolean loggedIn = false;
    private static boolean manager = false;
    private static String userID = "";

    private boolean taskComplete = false;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MaintenanceFragment newInstance(int sectionNumber)
    {
        MaintenanceFragment fragment = new MaintenanceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MaintenanceFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        userID = getArguments().getString( "userID", "");
        manager = getArguments().getBoolean( "manager", false);
        loggedIn = getArguments().getBoolean( "loggedIn", false);

        View rootView = inflater.inflate(R.layout.fragment_maintenance, container, false);

        return rootView;
    }

}
