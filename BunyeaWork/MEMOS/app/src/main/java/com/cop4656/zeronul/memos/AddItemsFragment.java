package com.cop4656.zeronul.memos;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Created by Zero Nul (Jason D. Bunyea) on 7/19/2015.
 * Add Items fragment will allow manager users to add
 * technologist, intruments, and procedures to the database.
 */
public class AddItemsFragment extends Fragment implements OnItemSelectedListener, View.OnClickListener
{
    //strings to hold the input of information and pass to database
    String entryID;
    String entryFirstName;
    String entryLastname;
    String entryPosition;
    String entryProcedure;
    String entryInstrumentID;
    String entryModel;
    String entryFrequency;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    //widget creation
    private Spinner itemSpinner;
    private LinearLayout newManager;
    private EditText managerID;
    private EditText managerFirstName;
    private EditText managerLastName;
    private EditText managerPassword;
    private EditText managerEmail;
    private LinearLayout newTechnologist;
    private EditText technologistID;
    private EditText technologistFirstName;
    private EditText technologistLastName;
    private EditText technologistPassword;
    private LinearLayout newInstrument;
    private EditText instrumentID;
    private EditText instrumentModel;
    private LinearLayout newProcedure;
    private EditText procedureName;
    private EditText frequency;
    private EditText instrumentPerformedOn;
    private Button addButton;

    public DatabaseAdapter myDB;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AddItemsFragment newInstance(int sectionNumber)
    {
        AddItemsFragment fragment = new AddItemsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public AddItemsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_additems, container, false);

        //assigment of widgets; widget names are matched to @id names in .xml file
        itemSpinner = (Spinner)rootView.findViewById( R.id.itemSpinner );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (getActivity(), R.array.items_to_add,
                        android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.notifyDataSetChanged();
        itemSpinner.setAdapter(adapter);
        itemSpinner.setOnItemSelectedListener(this);

        newManager = (LinearLayout)rootView.findViewById( R.id.newManager);
        managerID = (EditText)rootView.findViewById( R.id.managerID );
        managerFirstName = (EditText)rootView.findViewById( R.id.managerFirstName );
        managerLastName = (EditText)rootView.findViewById( R.id.managerLastName );
        managerPassword = (EditText)rootView.findViewById( R.id.managerPassword );
        managerEmail = (EditText)rootView.findViewById( R.id.managerEmail );

        newTechnologist =(LinearLayout)rootView.findViewById( R.id.newTechnologist);
        technologistID = (EditText)rootView.findViewById( R.id.technologistID );
        technologistFirstName = (EditText)rootView.findViewById( R.id.technologistFirstName);
        technologistLastName = (EditText)rootView.findViewById( R.id.technologistLastName );
        technologistPassword = (EditText)rootView.findViewById( R.id.technologistPassword );

        newInstrument = (LinearLayout)rootView.findViewById( R.id.newInstrument);
        instrumentID = (EditText)rootView.findViewById( R.id.instrumentID );
        instrumentModel = (EditText)rootView.findViewById( R.id.instrumentModel );

        newProcedure = (LinearLayout)rootView.findViewById( R.id.newProcedure);
        procedureName = (EditText)rootView.findViewById( R.id.procedureName );
        frequency = (EditText)rootView.findViewById( R.id.frequency );
        instrumentPerformedOn = (EditText)rootView.findViewById( R.id.instrumentPerformedOn );

        addButton = (Button)rootView.findViewById( R.id.addButton );

        //ensure fields are blank
        clearFields();

        //open database
        openDB();

        //set manager flag for testing
        /********************************************************
        *               REMOVE ME AFTER TESTING!!!              *
        ********************************************************/
        ( (MainActivity)getActivity() ).setManager( true );

        //check if a user is logged in, and disable fields if they are not
        if( !( ( MainActivity )getActivity()).isLoggedIn() )
        {
            itemSpinner.setEnabled( false );
            addButton.setEnabled( false );
            newManager.setVisibility(View.GONE);
            //display message to log in
            CharSequence textMessage = ( getActivity().getString( R.string.login_error ) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();
        }

        //check that user logged in is a manager account, and disable fields if they are not
        if ( ( ( MainActivity )getActivity()).isLoggedIn()
                && !( ( MainActivity )getActivity()).isManager() )
        {
            itemSpinner.setEnabled( false );
            addButton.setEnabled( false );
            //display message that manager account is required
            CharSequence textMessage = ( getActivity().getString( R.string.manager_error ) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();
        }

        rootView.findViewById( R.id.addButton ).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    //perform actions based on user spinner choice
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        String spinnerChoice = parent.getItemAtPosition(pos).toString();

        switch ( spinnerChoice )
        {
            //if manager is selected from spinner, make newManager fields visible
            case "Manager":
                //verify logged in and manager status again
                //this is necessary due to the first item of spinner
                //being selected during inflation of the fragment
                //while submit button is disabled and should prevent insertion of data by an
                //unauthorized account, better safe than sorry and presents a consistent form
                if ( ( ( MainActivity )getActivity()).isLoggedIn()
                        && ( ( MainActivity )getActivity()).isManager() )
                {
                    newManager.setVisibility(View.VISIBLE);
                    newManager.setEnabled(true);
                }
                //remaining fields are not visible
                newTechnologist.setVisibility(View.GONE);
                newTechnologist.setEnabled(false);
                newInstrument.setVisibility(View.GONE);
                newInstrument.setEnabled(false);
                newProcedure.setVisibility(View.GONE);
                newProcedure.setEnabled(false);
                break;
            //technologist selection will make newTechnologist fields visible and other fields not
            case "Technologist":
                newManager.setVisibility(View.GONE);
                newManager.setEnabled(false);
                newTechnologist.setVisibility(View.VISIBLE);
                newTechnologist.setEnabled(true);
                newInstrument.setVisibility(View.GONE);
                newInstrument.setEnabled(false);
                newProcedure.setVisibility(View.GONE);
                newProcedure.setEnabled(false);
                break;
            //instrument selection will make newInstrument fields visible and other fields not
            case "Instrument":
                newManager.setVisibility(View.GONE);
                newManager.setEnabled(false);
                newTechnologist.setVisibility(View.GONE);
                newTechnologist.setEnabled(false);
                newInstrument.setVisibility(View.VISIBLE);
                newInstrument.setEnabled(true);
                newProcedure.setVisibility(View.GONE);
                newProcedure.setEnabled(false);
                break;
            //procedure selection will make newProcedure fields visible and other fields not
            case "Procedure":
                newManager.setVisibility(View.GONE);
                newManager.setEnabled(false);
                newTechnologist.setVisibility(View.GONE);
                newTechnologist.setEnabled(false);
                newInstrument.setVisibility(View.GONE);
                newInstrument.setEnabled(false);
                newProcedure.setVisibility(View.VISIBLE);
                newProcedure.setEnabled(true);
                break;
        }

    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        // Another interface callback
    }

    //when submit button is clicked
    @Override
    public void onClick(View v)
    {
        //check for empty fields
        if ( fieldIsEmpty() )
        {
            CharSequence textMessage = ( getActivity().getString( R.string.empty_field_error) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();
        }

        //if fields are not empty, insert data into database
        /**
         * ********************* NEED METHOD TO INSERT!!!*****************************
         */
        else
        {
            //insert to database
            addItemToDatabase();

            CharSequence textMessage = "Stuff is inserted into database";
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            //toast.show();

            clearFields();
        }
    }

    //method to clear all edit text fields
    private void clearFields()
    {
        managerID.setText("");
        managerFirstName.setText("");
        managerLastName.setText("");
        managerPassword.setText("");
        managerEmail.setText( "" );

        technologistID.setText("");
        technologistFirstName.setText("");
        technologistLastName.setText("");
        technologistPassword.setText("");

        instrumentID.setText("");
        instrumentModel.setText("");

        procedureName.setText("");
        instrumentPerformedOn.setText("");
        frequency.setText( "" );
    }

    //method to check for empty edit text fields
    private boolean fieldIsEmpty()
    {
        String fieldChecker = managerID.getText().toString();;

        if ( newManager.isEnabled() )
        {
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = managerFirstName.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = managerLastName.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = managerPassword.getText().toString();
            if ( fieldChecker.equals( "" ) )
               return true;
            fieldChecker = managerEmail.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
        }

        else if ( newTechnologist.isEnabled() )
        {
            fieldChecker = technologistID.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = technologistFirstName.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = technologistLastName.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = technologistPassword.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
        }

        else if ( newInstrument.isEnabled() )
        {
            fieldChecker = instrumentID.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = instrumentModel.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
        }

        else
        {
            fieldChecker = procedureName.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = instrumentPerformedOn.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
            fieldChecker = frequency.getText().toString();
            if ( fieldChecker.equals( "" ) )
                return true;
        }

        return false;
    }

    private void openDB()
    {
        myDB = new DatabaseAdapter(this.getActivity());
        myDB.open();
    }

    private void closeDB() {
        myDB.close();
    }

    //add items like manager, tech, instrument, and procedure to database
    public void addItemToDatabase()
    {
        if(newManager.isEnabled())
        {
            //Create manager
            Manager m = new Manager(managerID.getText().toString(),
                    managerFirstName.getText().toString(), managerLastName.getText().toString(),
                    managerEmail.getText().toString(),0,managerPassword.getText().toString());

            //Add manager to database
            myDB.addManager(m);

            //create  added manager
            Manager addedManager = myDB.getManagerById(m.getEmployeeID());

            //send message via toast
            CharSequence textMessage = "Added Manager: " + addedManager.getEmployeeID() + '\n'
                    + addedManager.getLastName() + ", " + addedManager.getFirstName() + '\n'
                    + addedManager.getEmail() + ", " + addedManager.getPassword();
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();

        }
        else if(newTechnologist.isEnabled())
        {
            Technologist t= new Technologist(technologistID.getText().toString(),
                    technologistFirstName.getText().toString(), technologistLastName.getText().toString(),
                    "emailNotNeeded",technologistPassword.getText().toString());

            myDB.addTech(t);

            Technologist addedTech = myDB.getTechForID(t.getEmployeeID());

            //send message via toast
            CharSequence textMessage = "Added Technologist: " + addedTech.getEmployeeID() + '\n'
                    + addedTech.getLastName() + ", " + addedTech.getFirstName() + '\n'
                    + addedTech.getPassword();

            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, textMessage, duration);
            toast.show();

        }
        else if(newInstrument.isEnabled())
        {
            System.out.println("This is not where I want to be");
            Instrument i = new Instrument(instrumentID.getText().toString(),instrumentModel.getText().toString());
            boolean instAdded = myDB.addInstrument(i);

            Instrument addedInstrument = myDB.getInstrumentById(i.getInstrumentID());

            System.out.println(instAdded);

            //send message via toast
            CharSequence textMessage = "Added Instrument: " + addedInstrument.getInstrumentID() + ", " + addedInstrument.getModel();
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, textMessage, duration);
            toast.show();
        }
        else if(newProcedure.isEnabled())
        {

            Procedure p = new Procedure(procedureName.getText().toString(),instrumentPerformedOn.getText().toString(),frequency.getText().toString());
            myDB.addProcedure(p);

            Procedure addedProcedure = myDB.getProcedureByName(p.getProcedureName());

            //send message via toast
            CharSequence textMessage = "Added Procedure: " + addedProcedure.getProcedureName() + ", " + addedProcedure.getFrequency();
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, textMessage, duration);
            toast.show();
        }

    }

}
