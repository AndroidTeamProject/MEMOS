package com.cop4656.zeronul.memos;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Zero Nul (Jason D. Bunyea) on 7/16/2015. This is the home screen that will allow
 * users to log in.
 */
public class HomeFragment extends Fragment implements View.OnClickListener
{
    //create widgets
    Button loginButton;
    EditText userIDField;
    EditText passwordField;

    //database instance
    private DatabaseAdapter myDB;

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
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //assigment of widgets
        loginButton = (Button)rootView.findViewById( R.id.loginButton );
        userIDField = (EditText)rootView.findViewById( R.id.userID );
        passwordField = (EditText)rootView.findViewById( R.id.password );

        rootView.findViewById( R.id.loginButton ).setOnClickListener(this);

        //open the database
        openDB();

        //check if a user is already logged in
        //this allows a user to come back to the home screen to log out
        if ( ( ( MainActivity )getActivity() ).isLoggedIn() )
            //"log in" again with existing login information
            login( ( ( MainActivity )getActivity() ).getUserID() );

        return rootView;
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    //log in method
    //*********************NEED METHOD TO VERIFY USER ACCOUNT!!!***************************
    public void login()
    {
        loginButton.setText(R.string.logout);
        ( ( MainActivity )getActivity() ).setLoggedIn(true);
        ( ( MainActivity )getActivity() ).setUserID(userIDField.getText().toString());
        userIDField.setText( ( ( MainActivity )getActivity() ).getUserID() );
        userIDField.setClickable(false);
        passwordField.setVisibility(View.GONE);
    }

    //overloaded login method to handle inflation when a user is already logged in
    public void login( String userID )
    {
        loginButton.setText(R.string.logout);
        userIDField.setText( ( ( MainActivity )getActivity() ).getUserID() );
        userIDField.setClickable(false);
        passwordField.setVisibility(View.GONE);
    }

    //log out method
    public void logout()
    {
        loginButton.setText(R.string.login);
        ( ( MainActivity )getActivity() ).setLoggedIn(false);
        ( ( MainActivity )getActivity() ).setUserID("");
        userIDField.setText( "" );
        userIDField.setClickable(true);
        passwordField.setVisibility(View.VISIBLE);
    }

    //handle click of login/logout button
    @Override
    public void onClick(View v)
    {
        if ( ( ( MainActivity )getActivity() ).isLoggedIn() )
        {
            logout();
        }

        else
        {
            login();
        }
    }

    private void openDB()
    {
        myDB = new DatabaseAdapter(this.getActivity());
        myDB.open();
    }

    private void closeDB() {
        myDB.close();
    }

}
