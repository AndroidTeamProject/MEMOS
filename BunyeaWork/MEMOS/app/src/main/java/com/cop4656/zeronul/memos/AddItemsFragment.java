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
 * Created by Zero Nul on 7/19/2015.
 */
public class AddItemsFragment extends Fragment implements OnItemSelectedListener, View.OnClickListener
{
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

    private Spinner itemSpinner;
    private LinearLayout newManager;
    private EditText managerID;
    private EditText managerFirstName;
    private EditText managerLastName;
    private EditText managerPosition;
    private LinearLayout newTechnologist;
    private EditText technologistID;
    private EditText technologistFirstName;
    private EditText technologistLastName;
    private EditText technologistPosition;
    private LinearLayout newInstrument;
    private EditText instrumentID;
    private EditText instrumentModel;
    private LinearLayout newProcedure;
    private EditText procedureName;
    private EditText frequency;
    private EditText instrumentPerformedOn;
    private Button addButton;

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
        managerPosition = (EditText)rootView.findViewById( R.id.managerPosition );

        newTechnologist =(LinearLayout)rootView.findViewById( R.id.newTechnologist);
        technologistID = (EditText)rootView.findViewById( R.id.technologistID );
        technologistFirstName = (EditText)rootView.findViewById( R.id.technologistFirstName);
        technologistLastName = (EditText)rootView.findViewById( R.id.technologistLastName );
        technologistPosition = (EditText)rootView.findViewById( R.id.technologistPosition );

        newInstrument = (LinearLayout)rootView.findViewById( R.id.newInstrument);
        instrumentID = (EditText)rootView.findViewById( R.id.instrumentID );
        instrumentModel = (EditText)rootView.findViewById( R.id.instrumentModel );

        newProcedure = (LinearLayout)rootView.findViewById( R.id.newProcedure);
        procedureName = (EditText)rootView.findViewById( R.id.procedureName );
        frequency = (EditText)rootView.findViewById( R.id.frequency );
        instrumentPerformedOn = (EditText)rootView.findViewById( R.id.instrumentPerformedOn );

        addButton = (Button)rootView.findViewById( R.id.addButton );

        clearFields();

        /********************************************************
        *               REMOVE ME AFTER TESTING!!!              *
        ********************************************************/

        ( (MainActivity)getActivity() ).setManager( true );

        if( !( ( MainActivity )getActivity()).isLoggedIn() )
        {
            itemSpinner.setEnabled( false );
            addButton.setEnabled( false );
            newManager.setVisibility(View.GONE);
            CharSequence textMessage = ( getActivity().getString( R.string.login_error ) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();
        }

        if ( ( ( MainActivity )getActivity()).isLoggedIn()
                && !( ( MainActivity )getActivity()).isManager() )
        {
            itemSpinner.setEnabled( false );
            addButton.setEnabled( false );
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        String spinnerChoice = parent.getItemAtPosition(pos).toString();

        switch ( spinnerChoice )
        {
            case "Manager":
                if ( ( ( MainActivity )getActivity()).isLoggedIn()
                        && ( ( MainActivity )getActivity()).isManager() )
                {
                    newManager.setVisibility(View.VISIBLE);
                    newManager.setEnabled(true);
                }
                newTechnologist.setVisibility(View.GONE);
                newTechnologist.setEnabled(false);
                newInstrument.setVisibility(View.GONE);
                newInstrument.setEnabled(false);
                newProcedure.setVisibility(View.GONE);
                newProcedure.setEnabled(false);
                break;
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

    @Override
    public void onClick(View v)
    {
        if ( fieldIsEmpty() )
        {
            CharSequence textMessage = ( getActivity().getString( R.string.empty_field_error) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();
        }

        else
        {
            CharSequence textMessage = "Stuff is inserted into database";
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();

            clearFields();
        }
    }

    private void clearFields()
    {
        managerID.setText("");
        managerFirstName.setText("");
        managerLastName.setText("");
        managerPosition.setText("");

        technologistID.setText("");
        technologistFirstName.setText("");
        technologistLastName.setText("");
        technologistPosition.setText("");

        instrumentID.setText("");
        instrumentModel.setText("");

        procedureName.setText("");
        instrumentPerformedOn.setText("");
        frequency.setText( "" );
    }

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
            fieldChecker = managerPosition.getText().toString();
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
            fieldChecker = technologistPosition.getText().toString();
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
}
