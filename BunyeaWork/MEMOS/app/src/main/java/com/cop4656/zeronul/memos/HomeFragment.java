package com.cop4656.zeronul.memos;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Zero Nul on 7/16/2015.
 */
public class HomeFragment extends Fragment implements View.OnClickListener
{
    private static boolean loggedIn = false;
    private static boolean manager = false;
    private static String userID = "";
    private static String password = "";

    Button loginButton;
    EditText userIDField;
    EditText passwordField;

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

        loginButton = (Button)rootView.findViewById( R.id.loginButton );
        userIDField = (EditText)rootView.findViewById( R.id.userID );
        passwordField = (EditText)rootView.findViewById( R.id.password );

        rootView.findViewById( R.id.loginButton ).setOnClickListener(this);

        if ( ( ( MainActivity )getActivity() ).isLoggedIn() )
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

    public void setUserID( String userID )
    {
        this.userID = userID;
        Intent intent = new Intent( getActivity().getBaseContext(), MainActivity.class );
        intent.putExtra("userID", userID);
        getActivity().startActivity(intent);

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

    public void login()
    {
        loginButton.setText(R.string.logout);
        ( ( MainActivity )getActivity() ).setLoggedIn(true);
        ( ( MainActivity )getActivity() ).setUserID(userIDField.getText().toString());
        userIDField.setText( ( ( MainActivity )getActivity() ).getUserID() );
        userIDField.setClickable(false);
        passwordField.setVisibility(View.GONE);
    }

    public void login( String userID )
    {
        loginButton.setText(R.string.logout);
        userIDField.setText( ( ( MainActivity )getActivity() ).getUserID() );
        userIDField.setClickable(false);
        passwordField.setVisibility(View.GONE);
    }


    public void logout()
    {
        loginButton.setText(R.string.login);
        ( ( MainActivity )getActivity() ).setLoggedIn(false);
        ( ( MainActivity )getActivity() ).setUserID("");
        userIDField.setText( "" );
        userIDField.setClickable(true);
        passwordField.setVisibility(View.VISIBLE);
    }

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

    private boolean fieldIsEmpty()
    {
        String fieldChecker = userIDField.getText().toString();

        if ( fieldChecker.equals("") )
            return true;

        fieldChecker = passwordField.getText().toString();

        if ( fieldChecker.equals("") )
            return true;

        return false;
    }

}
