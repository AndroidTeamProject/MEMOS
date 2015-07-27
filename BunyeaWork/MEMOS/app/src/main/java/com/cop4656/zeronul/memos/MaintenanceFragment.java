package com.cop4656.zeronul.memos;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Zero Nul on 7/22/2015. This is the high use maintenance fragment that allows the
 * technologists to input information into the log table.
 */
public class MaintenanceFragment extends Fragment implements View.OnClickListener
{
    //strings to hold user entered maintenance data
    String maintenanceField;
    String maintenanceField2;
    String maintenanceField3;
    String maintenanceField4;
    String maintenanceField5;
    String maintenanceField6;
    String maintenanceField7;

    //declare widgets
    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    Button performMaintenance;


    //creation of date time objects
    Date dateObject = new Date();
    SimpleDateFormat dateFormatter = new SimpleDateFormat( "dd/MM/yyyy" );
    SimpleDateFormat timeFormatter = new SimpleDateFormat( "HH:mm" );

    String date = dateFormatter.format( dateObject );

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private DatabaseAdapter myDatabase;
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
        View rootView = inflater.inflate(R.layout.fragment_maintenance, container, false);

        //assign widgets
        editText = (EditText)rootView.findViewById( R.id.editText );
        editText2 = (EditText)rootView.findViewById( R.id.editText2 );
        editText3 = (EditText)rootView.findViewById( R.id.editText3 );
        editText4 = (EditText)rootView.findViewById( R.id.editText4 );
        editText5 = (EditText)rootView.findViewById( R.id.editText5 );
        editText6 = (EditText)rootView.findViewById( R.id.editText6 );
        editText7 = (EditText)rootView.findViewById( R.id.editText7 );
        performMaintenance = (Button)rootView.findViewById( R.id.performMaintenance );

        openDB();

        /********************************************************
         *               REMOVE ME AFTER TESTING!!!              *
         ********************************************************/
        ( (MainActivity)getActivity() ).setManager(false);

        //verify user is logged in, and disable fields if not
        if( !( ( MainActivity )getActivity()).isLoggedIn() )
        {
            disableFields();
            CharSequence textMessage = ( getActivity().getString( R.string.login_error ) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toastScore = Toast.makeText( context, textMessage, duration );
            toastScore.show();
        }

        //verify user is a technologist
        if ( ( ( MainActivity )getActivity()).isLoggedIn()
                && ( ( MainActivity )getActivity()).isManager() )
        {
            disableFields();
            CharSequence textMessage = ( getActivity().getString( R.string.technologist_error ) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toastScore = Toast.makeText( context, textMessage, duration );
            toastScore.show();
        }

        rootView.findViewById( R.id.performMaintenance ).setOnClickListener(this);

        //ensure fields are blank
        clearFields();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    //method to disable fields
    private void disableFields()
    {
        editText.setEnabled(false);
        editText2.setEnabled(false);
        editText3.setEnabled(false);
        editText4.setEnabled(false);
        editText5.setEnabled(false);
        editText6.setEnabled(false);
        editText7.setEnabled(false);
        performMaintenance.setEnabled(false);
    }

    //method to clear fields
    private void clearFields()
    {
        Date dateObject = new Date();
        String date = dateFormatter.format( dateObject );
        String time = timeFormatter.format( dateObject );
        editText.setText("");
        editText2.setText(((MainActivity) getActivity()).getUserID());
        editText3.setText(date);
        editText4.setText(time);
        editText5.setText("");
        editText6.setText("");
        editText7.setText("");

        editText3.setEnabled(false);
        editText4.setEnabled(false);
    }

    //method to check if field has been left blank
    private boolean fieldIsEmpty()
    {
        String fieldChecker = editText.getText().toString();
        if ( fieldChecker.equals("") )
            return true;
        fieldChecker = editText2.getText().toString();
        if ( fieldChecker.equals("") )
            return true;
        fieldChecker = editText3.getText().toString();
        if ( fieldChecker.equals("") )
            return true;
        fieldChecker = editText4.getText().toString();
        if ( fieldChecker.equals("") )
            return true;
        fieldChecker = editText5.getText().toString();
        if ( fieldChecker.equals("") )
            return true;
        fieldChecker = editText6.getText().toString();
        if ( fieldChecker.equals("") )
            return true;
        else
            return false;
    }

    //handle click of submit button
    @Override
    public void onClick(View v)
    {
        //check all fields have been entered, this will allow the Comments field to be left blank
        if ( fieldIsEmpty() )
        {
            CharSequence textMessage = ( getActivity().getString( R.string.empty_field_error) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toastScore = Toast.makeText( context, textMessage, duration );
            toastScore.show();
        }

        //handle insertion of data into database
        //**************************NEED METHOD TO INSERT DATA!!!****************************
        else
        {   //Log(int instrumentID, int procedureID,int techID,String date, String time, String shift, String comment)
            Log l = new Log(1, Integer.parseInt(editText.getText().toString()),Integer.parseInt(editText5.getText().toString()),
                    Integer.parseInt(editText2.getText().toString()),editText3.getText().toString(),editText4.getText().toString(),
                    editText6.getText().toString(), editText7.getText().toString());

            //put log in the database
            myDatabase.addLog(l);

            //display a log(not the actual current log)
            //Log addedLog = myDatabase.getLogByID(3);

            //show it in a toast
            /*
            CharSequence textMessage = addedLog.getInstrumentID() + " " + addedLog.getProcedureID() + " " + addedLog.getTechID() + " " + addedLog.getDate();
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toastScore = Toast.makeText( context, textMessage, duration );
            toastScore.show();
            */
            clearFields();
        }
    }

    private void openDB()
    {
        myDatabase = new DatabaseAdapter(this.getActivity());
        myDatabase.open();
    }

    private void closeDB() {
        myDatabase.close();
    }

}
