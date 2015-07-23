package com.cop4656.zeronul.memos;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Zero Nul on 7/21/2015.
 */
public class ReportsFragment extends Fragment
{
    private static boolean loggedIn = false;
    private static boolean manager = false;
    private static String userID = "";

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ReportsFragment newInstance(int sectionNumber)
    {
        ReportsFragment fragment = new ReportsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ReportsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        userID = getArguments().getString( "userID", "");
        manager = getArguments().getBoolean( "manager", false);
        loggedIn = getArguments().getBoolean( "loggedIn", false);

        View rootView = inflater.inflate(R.layout.fragment_reports, container, false);

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
